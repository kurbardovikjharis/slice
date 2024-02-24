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
internal class SensorDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    getSensorDetailsInteractor: GetRestaurantDetailsInteractor
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

    val state: StateFlow<SensorDetailsViewState> = combine(
        getSensorDetailsInteractor.flow,
        isPM10Checked,
        isPM25Checked
    ) { sensorDetailsResult, isPM10Checked, isPM25Checked ->
        val data = sensorDetailsResult.data
        val detailsData = DetailsData(data?.name ?: "")
        when (sensorDetailsResult) {
            is Result.Success -> {
                SensorDetailsViewState.Success(detailsData)
            }

            is Result.Loading -> {
                SensorDetailsViewState.Loading(detailsData)
            }

            is Result.Error -> {
                SensorDetailsViewState.Error(
                    sensorDetailsResult.message ?: "",
                    detailsData
                )
            }

            is Result.None -> SensorDetailsViewState.Empty
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = SensorDetailsViewState.Empty
    )
}

@Immutable
internal sealed interface SensorDetailsViewState {

    data class Success(
        val data: DetailsData
    ) : SensorDetailsViewState

    data class Error(
        val message: String, val data: DetailsData
    ) : SensorDetailsViewState

    data class Loading(val data: DetailsData) : SensorDetailsViewState

    data object Empty : SensorDetailsViewState
}

@Immutable
internal data class DetailsData(
    val name: String = ""
)