package com.jessica.tictactoe.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jessica.tictactoe.R
import com.jessica.tictactoe.data.datastore.DataStoryRepository
import com.jessica.tictactoe.domain.usecase.SetPlayerUseCase
import com.jessica.tictactoe.ui.previews.Previews
import com.jessica.tictactoe.ui.theme.TicTacToeDesignSystem
import com.jessica.tictactoe.ui.theme.TicTacToeTheme
import com.jessica.tictactoe.ui.theme.palette.system

@Composable
internal fun HomeScreen(
    viewModel: HomeViewModel,
    navigateToGame: () -> Unit,
    navigateToMatchHistory: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxSize()
            .background(TicTacToeDesignSystem.color.system.background.composeColor())
            .padding(TicTacToeDesignSystem.spacing.spacingMedium64)
    ) {
        TitleHomeScreen()

        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_x),
                contentDescription = "Icon X",
                modifier = Modifier.size(80.dp)
            )

            Image(
                painter = painterResource(id = R.drawable.ic_o),
                contentDescription = "Icon O",
                modifier = Modifier.size(80.dp)
            )
        }

        PlayersForm(
            onGame = { playerX, playerO ->
                viewModel.updatePlayerX(playerX)
                viewModel.updatePlayerO(playerO)
                navigateToGame()
            },
            onMatchHistory = { navigateToMatchHistory() }
        )
    }
}

@Previews
@Composable
private fun HomeScreenPreview() {
    val context = LocalContext.current
    val dataStoryRepository = DataStoryRepository(context)
    val setPlayerUseCase = SetPlayerUseCase(dataStoryRepository)
    val viewModel: MockHomeViewModel = viewModel(factory = MockHomeViewModel.create(setPlayerUseCase))
    TicTacToeTheme {
        HomeScreen(
            viewModel = viewModel,
            navigateToGame = { },
            navigateToMatchHistory = { }
        )
    }
}
