import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.jessica.tictactoe.ui.previews.Previews
import com.jessica.tictactoe.ui.theme.TicTacToeDesignSystem
import com.jessica.tictactoe.ui.theme.palette.neutral
import com.jessica.tictactoe.ui.theme.palette.whiteSolid

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleTextFieldComponent(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = label) },
        placeholder = { Text(text = placeholder) },
        modifier = Modifier
            .clip(
                RoundedCornerShape(20.dp)
            ),
        textStyle = TicTacToeDesignSystem.typography.baseStrong12,
        colors = TextFieldDefaults.textFieldColors(
            cursorColor = TicTacToeDesignSystem.color.neutral.scale300.composeColor(),
            focusedIndicatorColor = TicTacToeDesignSystem.color.neutral.scale300.composeColor(),
            unfocusedIndicatorColor = TicTacToeDesignSystem.color.neutral.scale300.composeColor(),
            containerColor = TicTacToeDesignSystem.color.whiteSolid.scale100.composeColor()
        ),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(
            onDone = {
                keyboardController?.hide()
            }
        )
    )
}


@Previews
@Composable
private fun SimpleTextFieldComponentPreview() {
    SimpleTextFieldComponent(
        value = "",
        label = "Label",
        placeholder = "Placeholder",
        onValueChange = { }
    )
}
