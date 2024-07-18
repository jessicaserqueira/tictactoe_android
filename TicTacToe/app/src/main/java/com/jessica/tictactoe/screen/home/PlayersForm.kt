package com.jessica.tictactoe.screen.home

import SimpleButtonComponent
import SimpleTextFieldComponent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.jessica.tictactoe.R
import com.jessica.tictactoe.ui.previews.Previews
import com.jessica.tictactoe.ui.theme.TicTacToeDesignSystem
import com.jessica.tictactoe.ui.theme.TicTacToeTheme
import com.jessica.tictactoe.ui.theme.palette.blue
import com.jessica.tictactoe.ui.theme.palette.cyan
import com.jessica.tictactoe.ui.theme.palette.whiteSolid


@Composable
internal fun PlayersForm(
    onGame: (playerX: String, player0: String) -> Unit,
    onMatchHistory: () -> Unit
) {
    var playerX by remember { mutableStateOf("") }
    var player0 by remember { mutableStateOf("") }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(TicTacToeDesignSystem.spacing.spacingMedium32),
        modifier = Modifier
            .padding(horizontal = TicTacToeDesignSystem.spacing.spacingSmall16)
    ) {

            SimpleTextFieldComponent(
                value = playerX,
                label = stringResource(id = R.string.player_x),
                placeholder = stringResource(id = R.string.enter_your_name),
                onValueChange = { playerX = it }
            )

            SimpleTextFieldComponent(
                value = player0,
                label = stringResource(id = R.string.player_0),
                placeholder = stringResource(id = R.string.enter_your_name),
                onValueChange = { player0 = it }
            )

        SimpleButtonComponent(
            modifier = Modifier
                .width(204.dp)
                .height(48.dp),
            text = stringResource(id = R.string.lets_play),
            backgroundColor = TicTacToeDesignSystem.color.cyan.scale400.composeColor(),
            textColor = TicTacToeDesignSystem.color.whiteSolid.scale600.composeColor(),
            isEnabled = true,
            onClick = {
                onGame(playerX, player0)
            },
        )


        SimpleButtonComponent(
            modifier = Modifier
                .width(204.dp)
                .height(48.dp),
            text = stringResource(id = R.string.match_history),
            backgroundColor = TicTacToeDesignSystem.color.cyan.scale200.composeColor(),
            textColor = TicTacToeDesignSystem.color.whiteSolid.scale600.composeColor(),
            isEnabled = true,
            onClick = {
              onMatchHistory()
            },
        )
    }
}

@Previews
@Composable
private fun Preview() {
    TicTacToeTheme {
        PlayersForm(
            onGame = { _, _ -> },
            onMatchHistory = { }
        )
    }
}