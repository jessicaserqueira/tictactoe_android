import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.jessica.tictactoe.R
import com.jessica.tictactoe.ui.previews.Previews
import com.jessica.tictactoe.ui.theme.TicTacToeDesignSystem
import com.jessica.tictactoe.ui.theme.palette.purple
import com.jessica.tictactoe.ui.theme.palette.whiteSolid

@Composable
fun BasicCardOptionItem(
    modifier: Modifier = Modifier,
    backgroundColor: Color? = null,
    leftSlot: @Composable (() -> Unit)? = null,
    title: String,
    rightSlot: @Composable (() -> Unit)? = null,
) {
    val defaultCardCornerRadius = 12.dp

    Column(
        modifier = modifier
            .clip(RoundedCornerShape(defaultCardCornerRadius))
            .background(backgroundColor ?: Color.Transparent)
            .padding(horizontal = TicTacToeDesignSystem.spacing.spacingSmall16)
            .width(115.dp)
            .height(175.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        leftSlot?.let { slot ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                slot()

            }
        }

        Text(
            text = title,
            maxLines = 2,
            color = TicTacToeDesignSystem.color.whiteSolid.scale500.composeColor(),
            style = TicTacToeDesignSystem.typography.baseStrong12,
            modifier = Modifier.padding(vertical = TicTacToeDesignSystem.spacing.spacingSmall16)
        )
        HorizontalDivider(
            color = TicTacToeDesignSystem.color.whiteSolid.scale500.composeColor(),
            thickness = 2.dp,
            modifier = Modifier.padding(horizontal = TicTacToeDesignSystem.spacing.spacingSmall16)
        )

        rightSlot?.let { slot ->

            Spacer(modifier = Modifier.height(TicTacToeDesignSystem.spacing.spacingSmall12))

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                slot()
            }
        }
    }
}

@Previews
@Composable
private fun PreviewBasicCardOptionItems() {
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        BasicCardOptionItem(
            backgroundColor = TicTacToeDesignSystem.color.purple.scale100.composeColor(),
            leftSlot = {
                Image(
                    painter = painterResource(id = R.drawable.avatar_1),
                    contentDescription = "Icon",
                    modifier = Modifier.size(48.dp)
                )
            },
            title = "Simple Title",
        )

        BasicCardOptionItem(
            backgroundColor = TicTacToeDesignSystem.color.purple.scale300.composeColor(),
            leftSlot = {
                Image(
                    painter = painterResource(id = R.drawable.avatar_1),
                    contentDescription = "Icon",
                    modifier = Modifier.size(48.dp)
                )
            },
            title = "Item with Subtitle",
            rightSlot = {
                Image(
                    painter = painterResource(id = R.drawable.ic_x),
                    contentDescription = "Icon",
                    modifier = Modifier.size(48.dp)
                )
            }
        )

        BasicCardOptionItem(
            backgroundColor = TicTacToeDesignSystem.color.purple.scale400.composeColor(),
            title = "Item with Icon",
            leftSlot = {
                Image(
                    painter = painterResource(id = R.drawable.avatar_1),
                    contentDescription = "Icon",
                    modifier = Modifier.size(48.dp)
                )
            },
        )

        BasicCardOptionItem(
            backgroundColor = TicTacToeDesignSystem.color.purple.scale200.composeColor(),
            title = "Item with Switch",
        )

        BasicCardOptionItem(
            backgroundColor = TicTacToeDesignSystem.color.purple.scale600Contrast.composeColor(),
            title = "Item with Button",
        )
    }
}
