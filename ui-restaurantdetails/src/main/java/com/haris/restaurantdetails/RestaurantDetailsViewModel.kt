package com.haris.restaurantdetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.haris.data.Result
import com.haris.restaurantdetails.interactors.GetRestaurantDetailsInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.annotation.concurrent.Immutable
import javax.inject.Inject

const val ID = "id"

@HiltViewModel
internal class RestaurantDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getSensorDetailsInteractor: GetRestaurantDetailsInteractor
) : ViewModel() {

    private val id: String? = savedStateHandle.get<String>(ID)

    private val isPM10Checked = MutableStateFlow(true)
    private val isPM25Checked = MutableStateFlow(false)

    init {
        if (id != null) {
            viewModelScope.launch {
                getSensorDetailsInteractor(id)
            }
        }
    }

    val state: StateFlow<RestaurantDetailsViewState> = combine(
        getSensorDetailsInteractor.flow,
        isPM10Checked,
        isPM25Checked
    ) { sensorDetailsResult, isPM10Checked, isPM25Checked ->
        val data = sensorDetailsResult.data
        when (sensorDetailsResult) {
            is Result.Success -> {
                if (data != null) {
                    RestaurantDetailsViewState.Success(data)
                } else {
                    RestaurantDetailsViewState.Error(
                        sensorDetailsResult.message ?: "",
                        null
                    )
                }
            }

            is Result.Loading -> {
                RestaurantDetailsViewState.Loading(data)
            }

            is Result.Error -> {
                RestaurantDetailsViewState.Error(
                    sensorDetailsResult.message ?: "",
                    data
                )
            }

            is Result.None -> RestaurantDetailsViewState.Empty
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = RestaurantDetailsViewState.Empty
    )

    fun retry() {
        if (id != null) {
            viewModelScope.launch {
                getSensorDetailsInteractor(id)
            }
        }
    }
}

@Immutable
internal sealed interface RestaurantDetailsViewState {

    data class Success(
        val data: RestaurantDetailsEntity
    ) : RestaurantDetailsViewState

    data class Error(
        val message: String, val data: RestaurantDetailsEntity?
    ) : RestaurantDetailsViewState

    data class Loading(val data: RestaurantDetailsEntity?) : RestaurantDetailsViewState

    data object Empty : RestaurantDetailsViewState
}

@Immutable
internal data class RestaurantDetailsEntity(
    val id: String,
    val name: String,
    val url: String,
    val rating: String,
    val numberOfRatings: String,
    val time: String,
    val distance: String,
)
