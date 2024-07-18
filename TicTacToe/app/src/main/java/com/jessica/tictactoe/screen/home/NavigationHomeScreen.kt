package com.jessica.tictactoe.screen.home

import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jessica.tictactoe.data.datastore.DataStoryRepository
import com.jessica.tictactoe.domain.usecase.SetPlayerUseCase
import com.jessica.tictactoe.screen.Route
import com.jessica.tictactoe.screen.gametictactoe.navigateToGameTicTacToe
import com.jessica.tictactoe.screen.matchhistory.navigateToMatchHistory

internal fun NavGraphBuilder.navigationHomeScreen(navController: NavController) {
    composable(
        Route.HOME.name,
    ) { backStackEntry ->
        val context = LocalContext.current
        val dataStoryRepository = DataStoryRepository(context)
        val setPlayerUseCase = SetPlayerUseCase(dataStoryRepository)

        val viewModel: HomeViewModel = viewModel(
            viewModelStoreOwner = backStackEntry,
            factory = HomeViewModel.create(
                setPlayerUseCase = setPlayerUseCase
            )
        )
        HomeScreen(
            viewModel = viewModel,
            navigateToGame = {
                navController.navigateToGameTicTacToe()
            },
            navigateToMatchHistory = {
                navController.navigateToMatchHistory()
            }
        )
    }
}