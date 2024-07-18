package com.jessica.tictactoe.screen.gametictactoe

import GameTicTacToeScreen
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jessica.tictactoe.data.datastore.DataStoryRepository
import com.jessica.tictactoe.domain.usecase.GetPlayerUseCase
import com.jessica.tictactoe.domain.usecase.SaveMatchHistoryUseCase
import com.jessica.tictactoe.screen.Route

internal fun NavGraphBuilder.navigationGameTicTacToe(navController: NavController) {
    composable(
        route = Route.GAME_TICTACTOE.name
    ) {
        val context = LocalContext.current
        val dataStoryRepository = DataStoryRepository(context)
        val getPlayerUseCase = GetPlayerUseCase(dataStoryRepository)
        val saveMatchHistoryUseCase = SaveMatchHistoryUseCase(dataStoryRepository)

        val viewModel: GameTicTacToeViewModel = viewModel(
            factory = GameTicTacToeViewModel.create(
                getPlayerUseCase = getPlayerUseCase,
                saveMatchHistoryUseCase = saveMatchHistoryUseCase
            )
        )

        GameTicTacToeScreen(
            viewModel = viewModel,
            onBackPressed = { navController.navigateUp() }
        )
    }
}

internal fun NavController.navigateToGameTicTacToe() {
    navigate(Route.GAME_TICTACTOE.name)
}
