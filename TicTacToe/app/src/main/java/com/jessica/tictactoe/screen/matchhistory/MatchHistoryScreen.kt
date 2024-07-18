package com.jessica.tictactoe.screen.matchhistory

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jessica.tictactoe.R
import com.jessica.tictactoe.data.datastore.DataStoryRepository
import com.jessica.tictactoe.domain.model.MatchResults
import com.jessica.tictactoe.domain.usecase.GetMachHistoryUseCase
import com.jessica.tictactoe.ui.component.unofficial.PlayerComponent
import com.jessica.tictactoe.ui.previews.Previews
import com.jessica.tictactoe.ui.theme.TicTacToeDesignSystem
import com.jessica.tictactoe.ui.theme.TicTacToeTheme
import com.jessica.tictactoe.ui.theme.palette.blue
import com.jessica.tictactoe.ui.theme.palette.cyan
import com.jessica.tictactoe.ui.theme.palette.purple
import com.jessica.tictactoe.ui.theme.palette.red
import com.jessica.tictactoe.ui.theme.palette.system

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun MatchHistoryScreen(
    viewModel: MatchHistoryViewModel,
    onBackPressed: () -> Unit
) {
    val matchResults = viewModel.matchResults.collectAsState().value
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.match_history),
                        maxLines = 2,
                        color = TicTacToeDesignSystem.color.blue.scale600Contrast.composeColor(),
                        style = TicTacToeDesignSystem.typography.baseStrong28,
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackPressed) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            tint = TicTacToeDesignSystem.color.blue.scale600Contrast.composeColor(),
                            contentDescription = ""
                        )
                    }
                },
            )
        }
    ) { paddingValues ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .background(TicTacToeDesignSystem.color.system.background.composeColor())
                .padding(paddingValues)
                .fillMaxSize()
                .padding(TicTacToeDesignSystem.spacing.spacingSmall16)
                .verticalScroll(scrollState)
        ) {
            matchResults.forEach { match ->

                MatchHistoryItem(matchResult = match)

            }
        }
    }
}

@Composable
internal fun MatchHistoryItem(matchResult: MatchResults) {
    val playerXColor = TicTacToeDesignSystem.color.blue.scale600Contrast.composeColor()
    val playerOColor = TicTacToeDesignSystem.color.red.scale400.composeColor()
    val isDrawColor = TicTacToeDesignSystem.color.purple.scale600Contrast.composeColor()
    val playerXIcon = painterResource(id = R.drawable.avatar_2)
    val playerOIcon = painterResource(id = R.drawable.avatar_1)
    val defaultCardCornerRadius = 12.dp

    val isPlayerXWinner = matchResult.result == matchResult.playerX
    val isPlayerOWinner = matchResult.result == matchResult.playerO
    val isDraw = matchResult.result == "Empate"
    val winnerColor = when {
        isPlayerXWinner -> playerXColor
        isPlayerOWinner -> playerOColor
        isDraw -> isDrawColor
        else -> Color.Gray
    }
    val winnerIcon = when {
        isPlayerXWinner -> playerXIcon
        isPlayerOWinner -> playerOIcon
        else -> null
    }
    val winnerTitle = when {
        isDraw -> stringResource(id = R.string.draw_result)
        else -> stringResource(id = R.string.winner, matchResult.result)
        }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = TicTacToeDesignSystem.spacing.spacingSmall16)
            .clip(RoundedCornerShape(defaultCardCornerRadius))
            .border(
                width = 1.dp,
                color = TicTacToeDesignSystem.color.cyan.scale500.composeColor(),
                shape = RoundedCornerShape(defaultCardCornerRadius)
            )
    ) {
        PlayerComponent(
            title = stringResource(id = R.string.player_x_, matchResult.playerX),
            thumbImage = playerXIcon,
            textColor = playerXColor
        )

        PlayerComponent(
            title = stringResource(id = R.string.player_o, matchResult.playerO),
            thumbImage = playerOIcon,
            textColor = playerOColor
        )

        if (isDraw) {
            Text(
                text = stringResource(id = R.string.draw_result),
                style = TicTacToeDesignSystem.typography.baseStrong18,
                color = winnerColor,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(TicTacToeDesignSystem.spacing.spacingSmall16)
                    .align(Alignment.CenterHorizontally)
            )
        }

        if (winnerIcon != null) {
            PlayerComponent(
                title = winnerTitle,
                thumbImage = winnerIcon,
                textColor = winnerColor
            )
        }
        Text(
            text = stringResource(id = R.string.match_time, matchResult.timestamp),
            style = TicTacToeDesignSystem.typography.baseStrong18,
            color = TicTacToeDesignSystem.color.blue.scale600Contrast.composeColor(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(TicTacToeDesignSystem.spacing.spacingSmall16)
                .align(Alignment.CenterHorizontally)
        )
    }
}


@Previews
@Composable
fun PreviewMatchHistoryScreen() {
    val context = LocalContext.current
    val dataStoryRepository = DataStoryRepository(context)
    val getMachHistoryUseCase = GetMachHistoryUseCase(dataStoryRepository)

    val viewModel: MockMatchHistoryViewModel = viewModel(
        factory = MockMatchHistoryViewModel.create(
            getMachHistoryUseCase
        )
    )

    TicTacToeTheme {
        MatchHistoryScreen(
            viewModel = viewModel,
            onBackPressed = {}
        )
    }
}