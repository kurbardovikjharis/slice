package com.haris.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.haris.data.Result
import com.haris.search.data.Group
import com.haris.search.interactors.GetGroupsInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.annotation.concurrent.Immutable
import javax.inject.Inject

@HiltViewModel
internal class SensorsViewModel @Inject constructor(
    private val getGroupsInteractor: GetGroupsInteractor,
) : ViewModel() {

    init {
        viewModelScope.launch {
            getGroupsInteractor()
        }
    }

    val state: StateFlow<SensorsViewState> = getGroupsInteractor.flow.map {
        when (it) {
            is Result.Success -> {
                SensorsViewState.Success(
                    groups = it.data ?: emptyList()
                )
            }

            is Result.Loading -> {
                SensorsViewState.Loading(
                    groups = it.data
                )
            }

            is Result.Error -> {
                SensorsViewState.Error(
                    message = it.message ?: "",
                    groups = it.data
                )
            }

            is Result.None -> SensorsViewState.Empty
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = SensorsViewState.Empty
    )

    fun retry() {
        viewModelScope.launch {
            getGroupsInteractor()
        }
    }
}

@Immutable
internal sealed interface SensorsViewState {

    data class Success(
        val groups: List<Group>
    ) : SensorsViewState

    data class Error(
        val message: String, val groups: List<Group>?
    ) : SensorsViewState

    data class Loading(
        val groups: List<Group>?
    ) : SensorsViewState

    data object Empty : SensorsViewState
}

