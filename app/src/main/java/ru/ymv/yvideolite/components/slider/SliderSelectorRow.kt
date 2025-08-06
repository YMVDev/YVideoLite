package ru.ymv.yvideolite.components.slider

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircleOutline
import androidx.compose.material.icons.filled.RemoveCircleOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun SliderSelectorRow(
    onValueChange: (Float) -> Unit,
    onSelect: () -> Unit,
    currentValue: Float,
    range: ClosedFloatingPointRange<Float> = 0f..1f,
    modifier: Modifier = Modifier,
    contentColor: Color = Color.Unspecified,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        IconButton(
            onClick = {
                onValueChange(currentValue - 0.25f)
                onSelect()
            },
            modifier = Modifier.weight(0.15f),
        ) {
            Icon(
                Icons.Default.RemoveCircleOutline,
                "",
                tint = contentColor
            )
        }
        HorizontalBoxSlider(
            value = currentValue,
            onValueChange = { onValueChange(it) },
            onValueChangeFinished = onSelect,
            modifier = Modifier.weight(0.7f),
            valueRange = range,
            stepSize = 0.25f,
            activeColor = Color.White
        )
        IconButton(
            onClick = {
                onValueChange(currentValue + 0.25f)
                onSelect()
            },
            modifier = Modifier.weight(0.15f),
        ) {
            Icon(
                Icons.Default.AddCircleOutline,
                "",
                tint = contentColor
            )
        }
    }
}