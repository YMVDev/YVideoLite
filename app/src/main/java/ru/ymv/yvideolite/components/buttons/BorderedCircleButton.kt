package ru.ymv.yvideolite.components.buttons

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ru.ymv.yvideolite.components.text.AutoSizeText

/**
 * A circular button with a customizable border and text.
 *
 * This composable function creates a circular button with a distinct border and customizable text.
 * It allows customization of the size, shape, border appearance, text color, and click action.
 *
 * @param text The text to display on the button.
 * @param modifier Modifier to be applied to the button.
 * @param size The size of the button (both width and height). Defaults to 200.dp.
 * @param shape The shape of the button. Defaults to [CircleShape].
 * @param borderWidth The width of the button's border. Defaults to 2.dp.
 * @param borderColor The color of the button's border. Defaults to MaterialTheme.colorScheme.onSurface.
 * @param textColor The color of the button's text. Defaults to [borderColor].
 * @param onClick A callback invoked when the button is clicked.
 */
@Composable
fun BorderedCircleButton(
    text: String,
    modifier: Modifier = Modifier,
    size: Dp = 200.dp,
    shape: Shape = CircleShape,
    borderWidth: Dp = 2.dp,
    borderColor: Color = MaterialTheme.colorScheme.onSurface,
    textColor: Color = borderColor,
    onClick: () -> Unit = {},
) {
    Box(
        modifier = modifier
            .size(size)
            .border(
                width = borderWidth,
                color = borderColor,
                shape = shape
            )
            .clip(shape)
            .clickable(onClick = onClick)
    ) {
        AutoSizeText(
            text = text,
            color = textColor,
            modifier = Modifier
                .align(Alignment.Center)
                .padding(16.dp)
        )
    }
}