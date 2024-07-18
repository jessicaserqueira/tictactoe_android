package com.jessica.tictactoe.domain.usecase

import com.jessica.tictactoe.data.datastore.DataStoryRepository
import com.jessica.tictactoe.domain.model.MatchResults
import kotlinx.coroutines.flow.Flow

class GetMachHistoryUseCase(private val repository: DataStoryRepository) {
    operator fun invoke(): Flow<List<MatchResults>> {
        return repository.getMatchResults()
    }

}