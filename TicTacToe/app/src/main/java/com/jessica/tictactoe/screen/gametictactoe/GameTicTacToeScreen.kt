import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jessica.tictactoe.R
import com.jessica.tictactoe.data.datastore.DataStoryRepository
import com.jessica.tictactoe.domain.usecase.GetPlayerUseCase
import com.jessica.tictactoe.domain.usecase.SaveMatchHistoryUseCase
import com.jessica.tictactoe.screen.gametictactoe.GameTicTacToeViewModel
import com.jessica.tictactoe.screen.gametictactoe.MockGameTicTacToeViewModel
import com.jessica.tictactoe.ui.previews.Previews
import com.jessica.tictactoe.ui.theme.TicTacToeDesignSystem
import com.jessica.tictactoe.ui.theme.palette.blue
import com.jessica.tictactoe.ui.theme.palette.red
import com.jessica.tictactoe.ui.theme.palette.system
import com.jessica.tictactoe.ui.theme.palette.whiteSolid

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun GameTicTacToeScreen(
    viewModel: GameTicTacToeViewModel,
    onBackPressed: () -> Unit
) {
    val defaultCardCornerRadius = 12.dp

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "") },
                navigationIcon = {
                    IconButton(onClick = onBackPressed) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = ""
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()

        ) {
            Text(
                text = stringResource(id = R.string.tic_tac_toe),
                maxLines = 2,
                color = TicTacToeDesignSystem.color.blue.scale600Contrast.composeColor(),
                style = TicTacToeDesignSystem.typography.baseStrong48,
                modifier = Modifier.padding(vertical = TicTacToeDesignSystem.spacing.spacingSmall16)
            )

            Box(
                modifier = Modifier
                    .padding(horizontal = TicTacToeDesignSystem.spacing.spacingMedium32)
                    .clip(RoundedCornerShape(defaultCardCornerRadius))
            ) {
                HorizontalDivider(
                    color = TicTacToeDesignSystem.color.red.scale600Contrast.composeColor(),
                    thickness = 20.dp
                )
            }

            Row(
                modifier = Modifier.padding(TicTacToeDesignSystem.spacing.spacingSmall16)
            ) {
                BasicCardOptionItem(
                    backgroundColor = TicTacToeDesignSystem.color.blue.scale600Contrast.composeColor(),
                    leftSlot = {
                        Image(
                            painter = painterResource(id = R.drawable.avatar_2),
                            contentDescription = "Icon",
                            modifier = Modifier.size(48.dp)
                        )
                    },
                    title = viewModel.playerX.collectAsState().value,
                    rightSlot = {
                        Image(
                            painter = painterResource(id = R.drawable.ic_x),
                            contentDescription = "Icon",
                            modifier = Modifier.size(48.dp)
                        )
                    }
                )

                Spacer(modifier = Modifier.width(TicTacToeDesignSystem.spacing.spacingSmall24))

                BasicCardOptionItem(
                    backgroundColor = TicTacToeDesignSystem.color.red.scale400.composeColor(),
                    leftSlot = {
                        Image(
                            painter = painterResource(id = R.drawable.avatar_1),
                            contentDescription = "Icon",
                            modifier = Modifier.size(48.dp)
                        )
                    },
                    title = viewModel.playerO.collectAsState().value,
                    rightSlot = {
                        Image(
                            painter = painterResource(id = R.drawable.ic_o),
                            contentDescription = "Icon",
                            modifier = Modifier.size(48.dp)
                        )
                    }
                )
            }

            TicTacToeScreen(viewModel)
        }
    }
}

@Composable
internal fun TicTacToeScreen(
    viewModel: GameTicTacToeViewModel
) {
    val gridSize = 3
    val grid = remember { mutableStateListOf(*Array(gridSize * gridSize) { "" }) }
    val winner = remember { mutableStateOf("") }

    winner.value = viewModel.checkWinner(grid)

    if (winner.value.isNotEmpty()) {
        ShowWinnerDialog(winner.value) {

            grid.replaceAll { "" }
            winner.value = ""
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(TicTacToeDesignSystem.color.system.background.composeColor())
            .padding(TicTacToeDesignSystem.spacing.spacingSmall16),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .background(
                    TicTacToeDesignSystem.color.blue.scale600Contrast.composeColor(),
                    RoundedCornerShape(TicTacToeDesignSystem.spacing.spacingSmall12)
                )
                .padding(TicTacToeDesignSystem.spacing.spacingSmall8)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(TicTacToeDesignSystem.spacing.spacingSmall8)

            ) {
                for (i in 0 until gridSize) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(TicTacToeDesignSystem.spacing.spacingSmall8)
                    ) {
                        for (j in 0 until gridSize) {
                            val index = i * gridSize + j
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier
                                    .size(100.dp)
                                    .background(
                                        TicTacToeDesignSystem.color.whiteSolid.scale700.composeColor(),
                                        RoundedCornerShape(TicTacToeDesignSystem.spacing.spacingSmall12)
                                    )
                                    .clickable {
                                        if (grid[index].isEmpty()) {
                                            grid[index] =
                                                if (grid.count { it.isNotEmpty() } % 2 == 0) "X" else "O"
                                        }
                                    }
                            ) {
                                if (grid[index].isNotEmpty()) {
                                    Image(
                                        painter = getImageForSymbol(grid[index]),
                                        contentDescription = if (grid[index] == "X") "Cross" else "Nought"
                                    )
                                }
                            }

                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ShowWinnerDialog(winner: String, onRestart: () -> Unit) {
    if (winner.isNotEmpty()) {
        AlertDialog(
            onDismissRequest = {
                onRestart()
            },
            confirmButton = {
                TextButton(onClick = onRestart) {
                    Text(text = stringResource(id = R.string.restart))
                }
            },
            title = { Text(stringResource(id = R.string.game_over)) },
            text = {
                Text(
                    text = if (winner == "Empate" || winner == "Tie") {
                        stringResource(id = R.string.draw)
                    } else {
                        stringResource(id = R.string.winner_is, winner)
                    }
                )
            }
        )
    }
}


@Composable
fun getImageForSymbol(symbol: String): Painter {
    return if (symbol == "X") {
        painterResource(id = R.drawable.ic_x)
    } else {
        painterResource(id = R.drawable.ic_o)
    }
}

@Previews
@Composable
private fun GameTicTacToeScreenPreview() {
    val context = LocalContext.current
    val dataStoryRepository = DataStoryRepository(context)
    val getPlayerUseCase = GetPlayerUseCase(dataStoryRepository)
    val saveMatchHistoryUseCase = SaveMatchHistoryUseCase(dataStoryRepository)

   val viewModel: MockGameTicTacToeViewModel = viewModel(
       factory = MockGameTicTacToeViewModel.create(
           getPlayerUseCase,
           saveMatchHistoryUseCase
       )
   )

    GameTicTacToeScreen(
        viewModel = viewModel,
        onBackPressed = { }
    )
}
