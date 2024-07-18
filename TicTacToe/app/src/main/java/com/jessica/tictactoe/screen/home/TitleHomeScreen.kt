package com.jessica.tictactoe.screen.home


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.jessica.tictactoe.ui.theme.TicTacToeDesignSystem
import com.jessica.tictactoe.ui.theme.palette.cyan
import com.jessica.tictactoe.ui.theme.palette.red
import com.jessica.tictactoe.ui.theme.palette.yellow

@Composable
fun TitleHomeScreen() {
    val titleStyle = TicTacToeDesignSystem.typography.baseStrong48
    val title = buildAnnotatedString {
        "Tic".forEach { char ->
            withStyle(
                style = SpanStyle(
                    color = when (char) {
                        'T' -> TicTacToeDesignSystem.color.cyan.scale500.composeColor()
                        'i' -> TicTacToeDesignSystem.color.yellow.scale500.composeColor()
                        'c' -> TicTacToeDesignSystem.color.red.scale500.composeColor()
                        else -> Color.Black
                    },
                    fontSize = titleStyle.fontSize,
                    fontWeight = titleStyle.fontWeight,
                    fontStyle = titleStyle.fontStyle
                )
            ) {
                append(char)
            }
        }
        append("\n")

        "Tac".forEach { char ->
            withStyle(
                style = SpanStyle(
                    color = when (char) {
                        'T' -> TicTacToeDesignSystem.color.yellow.scale500.composeColor()
                        'a' -> TicTacToeDesignSystem.color.red.scale500.composeColor()
                        'c' -> TicTacToeDesignSystem.color.cyan.scale500.composeColor()
                        else -> Color.Black
                    },
                    fontSize = titleStyle.fontSize,
                    fontWeight = titleStyle.fontWeight,
                    fontStyle = titleStyle.fontStyle
                )
            ) {
                append(char)
            }
        }
        append("\n")


        "Toe".forEach { char ->
            withStyle(
                style = SpanStyle(
                    color = when (char) {
                        'T' -> TicTacToeDesignSystem.color.red.scale500.composeColor()
                        'o' -> TicTacToeDesignSystem.color.yellow.scale500.composeColor()
                        'e' -> TicTacToeDesignSystem.color.cyan.scale500.composeColor()
                        else -> Color.Black
                    },
                    fontSize = titleStyle.fontSize,
                    fontWeight = titleStyle.fontWeight,
                    fontStyle = titleStyle.fontStyle
                )
            ) {
                append(char)
            }
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(TicTacToeDesignSystem.spacing.spacingSmall16),
        modifier = Modifier
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = title,
            style = titleStyle,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}
