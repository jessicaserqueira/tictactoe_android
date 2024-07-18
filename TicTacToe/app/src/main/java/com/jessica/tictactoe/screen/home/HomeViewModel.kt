package com.jessica.tictactoe.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.jessica.tictactoe.data.datastore.PreferencesKeys
import com.jessica.tictactoe.domain.usecase.SetPlayerUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

internal open class HomeViewModel(
    private val setPlayerUseCase: SetPlayerUseCase,
) : ViewModel() {

    var playerX = MutableStateFlow("")
    var playerO = MutableStateFlow("")

    fun updatePlayerX(newName: String) {
        viewModelScope.launch {
            setPlayerUseCase(PreferencesKeys.PLAYER_X_KEY.toString(), newName)
            playerX.value = newName
        }
    }

    fun updatePlayerO(newName: String) {
        viewModelScope.launch {
            setPlayerUseCase(PreferencesKeys.PLAYER_O_KEY.toString(), newName)
            playerO.value = newName
        }
    }

    companion object {
        fun create(
            setPlayerUseCase: SetPlayerUseCase,
        ): ViewModelProvider.Factory = viewModelFactory {
            initializer {
                HomeViewModel(
                 setPlayerUseCase
                )
            }
        }
    }
}

internal class MockHomeViewModel(
    setPlayerUseCase: SetPlayerUseCase
): HomeViewModel(setPlayerUseCase) {
    companion object {
        fun create(
            setPlayerUseCase: SetPlayerUseCase,
        ): ViewModelProvider.Factory = viewModelFactory {
            initializer {
                MockHomeViewModel(setPlayerUseCase)
            }
        }
    }
}