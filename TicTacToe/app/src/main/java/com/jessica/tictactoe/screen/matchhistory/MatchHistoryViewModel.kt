package com.jessica.tictactoe.screen.matchhistory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.jessica.tictactoe.domain.model.MatchResults
import com.jessica.tictactoe.domain.usecase.GetMachHistoryUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

internal open class MatchHistoryViewModel(
    private val getMatchHistoryUseCase: GetMachHistoryUseCase
) : ViewModel() {

    val matchResults = MutableStateFlow<List<MatchResults>>(emptyList())

    init {
        fetchGameResults()
    }

    private fun fetchGameResults() {
        viewModelScope.launch {
            matchResults.value = getMatchHistoryUseCase().firstOrNull() ?: emptyList()
        }
    }

    companion object {
        fun create(getMatchHistoryUseCase: GetMachHistoryUseCase):
            ViewModelProvider.Factory = viewModelFactory {
            initializer {
                MatchHistoryViewModel(getMatchHistoryUseCase)
            }
        }
    }
}

internal class MockMatchHistoryViewModel(
 getMatchHistoryUseCase: GetMachHistoryUseCase
) : MatchHistoryViewModel(getMatchHistoryUseCase) {
    companion object {
        fun create(
            getMatchHistoryUseCase: GetMachHistoryUseCase
        ): ViewModelProvider.Factory = viewModelFactory {
            initializer {
                MockMatchHistoryViewModel(
               getMatchHistoryUseCase
                )
            }
        }
    }
}