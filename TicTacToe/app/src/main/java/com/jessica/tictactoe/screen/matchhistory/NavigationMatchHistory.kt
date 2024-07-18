package com.jessica.tictactoe.screen.matchhistory

import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jessica.tictactoe.data.datastore.DataStoryRepository
import com.jessica.tictactoe.domain.usecase.GetMachHistoryUseCase
import com.jessica.tictactoe.screen.Route

internal fun NavGraphBuilder.navigationMatchHistory(navController: NavController) {
    composable(
        route = Route.MATCH_HISTORY.name
    ) {
        val context = LocalContext.current
        val dataStoryRepository = DataStoryRepository(context)
        val getMachHistoryUseCase = GetMachHistoryUseCase(dataStoryRepository)

        val viewModel: MatchHistoryViewModel = viewModel(
            factory = MatchHistoryViewModel.create(
            getMachHistoryUseCase
            )
        )

        MatchHistoryScreen(
            viewModel = viewModel,
            onBackPressed = { navController.navigateUp() }
        )
    }
}

internal fun NavController.navigateToMatchHistory() {
    navigate(Route.MATCH_HISTORY.name)
}