package com.jessica.tictactoe.domain.usecase

import com.jessica.tictactoe.data.datastore.DataStoryRepository

class SetPlayerUseCase(private val repository: DataStoryRepository) {
    suspend operator fun invoke(key: String, name: String) {
        repository.savePlayer(key, name)
    }
}