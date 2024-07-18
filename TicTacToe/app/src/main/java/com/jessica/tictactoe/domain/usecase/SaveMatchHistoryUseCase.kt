package com.jessica.tictactoe.domain.usecase

import com.jessica.tictactoe.data.datastore.DataStoryRepository
import com.jessica.tictactoe.domain.model.MatchResults

class SaveMatchHistoryUseCase(private val repository: DataStoryRepository) {
    suspend operator fun invoke(matchResults: MatchResults) {
        repository.saveMatchResults(matchResults)
    }
}