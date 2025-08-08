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

/**
 * A row containing a slider and "+"/"-" buttons for discrete value selection.
 *
 * This composable provides a user interface for selecting a value within a specified range.
 * It includes a [HorizontalBoxSlider] for fine-grained control and "+"/"-" buttons for
 * incrementing or decrementing the value by a fixed step.
 *
 * @param onValueChange A callback invoked when the slider's value or a button's value changes.  The new value is passed as a parameter.
 * @param onSelect A callback invoked when the user confirms a selection (e.g., after releasing the slider or pressing a button).
 * @param currentValue The current value of the slider, which should be within the [range].
 * @param range The range of values that the slider can represent. Defaults to `0f..1f`.
 * @param modifier Modifier to be applied to the row.
 * @param contentColor The color to use for the icons. Defaults to `Color.Unspecified`.
 */
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