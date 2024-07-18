package com.jessica.tictactoe.domain.usecase

import com.jessica.tictactoe.data.datastore.DataStoryRepository
import kotlinx.coroutines.flow.Flow

class GetPlayerUseCase(private val repository: DataStoryRepository) {
    operator fun invoke(playerType: String): Flow<String?> {
        return repository.getPlayerName(playerType)
    }
}