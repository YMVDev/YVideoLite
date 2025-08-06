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

@Composable
fun BigCircularButton(
    text: String,
    modifier: Modifier = Modifier,
    size: Dp = 200.dp,
    shape: Shape = CircleShape,
    borderWidth: Dp = 2.dp,
    borderColor: Color = MaterialTheme.colorScheme.secondaryContainer,
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