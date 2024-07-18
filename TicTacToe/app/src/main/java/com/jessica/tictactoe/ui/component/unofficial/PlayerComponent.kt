package com.jessica.tictactoe.ui.component.unofficial

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.jessica.tictactoe.R
import com.jessica.tictactoe.ui.previews.Previews
import com.jessica.tictactoe.ui.theme.TicTacToeDesignSystem
import com.jessica.tictactoe.ui.theme.palette.neutral

@Composable
fun PlayerComponent(
    modifier: Modifier = Modifier,
    textColor: Color? = null,
    title: String?,
    thumbImage: Painter,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = thumbImage,
            contentDescription = "Icon",
            modifier =  Modifier
                .padding(vertical = TicTacToeDesignSystem.spacing.spacingSmall16)
                .padding(start = TicTacToeDesignSystem.spacing.spacingSmall16)
                .clip(CircleShape)
                .size(40.dp)
                .background(TicTacToeDesignSystem.color.neutral.scale100.composeColor()),
        )

        Text(
            modifier = modifier
                .weight(1f)
                .padding(horizontal = TicTacToeDesignSystem.spacing.spacingSmall16)
                .fillMaxWidth(),
            text = title.orEmpty(),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = TicTacToeDesignSystem.typography.baseStrong18,
            color = textColor ?: TicTacToeDesignSystem.color.neutral.scale100.composeColor()
        )
    }
}

@Previews
@Composable
private fun Preview() {
    PlayerComponent(
        title = "Taylor Swift",
        thumbImage = painterResource(id = R.drawable.avatar_1)
    )
}