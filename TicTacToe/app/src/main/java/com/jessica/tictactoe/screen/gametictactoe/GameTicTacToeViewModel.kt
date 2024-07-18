package com.jessica.tictactoe.screen.gametictactoe

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.jessica.tictactoe.data.datastore.PreferencesKeys
import com.jessica.tictactoe.domain.model.MatchResults
import com.jessica.tictactoe.domain.usecase.GetPlayerUseCase
import com.jessica.tictactoe.domain.usecase.SaveMatchHistoryUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

internal open class GameTicTacToeViewModel(
    private val getPlayerUseCase: GetPlayerUseCase,
    private val saveMatchHistoryUseCase: SaveMatchHistoryUseCase
) : ViewModel() {

    val playerX = MutableStateFlow("")
    val playerO = MutableStateFlow("")

    init {
        fetchPlayerNames()
    }

    private fun fetchPlayerNames() {
        viewModelScope.launch {
            getPlayerUseCase(PreferencesKeys.PLAYER_X_KEY.toString()).collect { name ->
                playerX.value = name ?: ""
            }
        }
        viewModelScope.launch {
            getPlayerUseCase(PreferencesKeys.PLAYER_O_KEY.toString()).collect { name ->
                playerO.value = name ?: ""
            }
        }
    }


    fun checkWinner(grid: List<String>): String {
        val lines = listOf(
            Triple(0, 1, 2), Triple(3, 4, 5), Triple(6, 7, 8), // Linhas
            Triple(0, 3, 6), Triple(1, 4, 7), Triple(2, 5, 8), // Colunas
            Triple(0, 4, 8), Triple(2, 4, 6)
        )

        for ((a, b, c) in lines) {
            if (grid[a] != "" && grid[a] == grid[b] && grid[a] == grid[c]) {
                val winner = if (grid[a] == "X") playerX.value else playerO.value
                viewModelScope.launch {
                    saveMatchHistoryUseCase(MatchResults(playerX.value, playerO.value, winner))
                }
                return winner
            }
        }

        if (grid.all { it.isNotEmpty() }){
            viewModelScope.launch {
                saveMatchHistoryUseCase(MatchResults(playerX.value, playerO.value, "Empate"))
            }
            return "Empate"
        }
        return ""
    }

    companion object {
        fun create(
            getPlayerUseCase: GetPlayerUseCase,
            saveMatchHistoryUseCase: SaveMatchHistoryUseCase
        ): ViewModelProvider.Factory = viewModelFactory {
            initializer {
                GameTicTacToeViewModel(
                    getPlayerUseCase = getPlayerUseCase,
                    saveMatchHistoryUseCase = saveMatchHistoryUseCase
                )
            }
        }
    }
}

internal class MockGameTicTacToeViewModel(
    getPlayerUseCase: GetPlayerUseCase,
    saveMatchHistoryUseCase: SaveMatchHistoryUseCase
) : GameTicTacToeViewModel(
    getPlayerUseCase,
    saveMatchHistoryUseCase
) {
    companion object {
        fun create(
            getPlayerUseCase: GetPlayerUseCase,
            saveMatchHistoryUseCase: SaveMatchHistoryUseCase
        ): ViewModelProvider.Factory = viewModelFactory {
            initializer {
                MockGameTicTacToeViewModel(
                    getPlayerUseCase = getPlayerUseCase,
                    saveMatchHistoryUseCase = saveMatchHistoryUseCase
                )
            }
        }
    }
}