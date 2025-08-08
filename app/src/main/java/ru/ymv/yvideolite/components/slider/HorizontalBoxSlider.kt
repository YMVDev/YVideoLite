package ru.ymv.yvideolite.components.slider

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ru.ymv.yvideolite.utils.extensions.toDp
import kotlin.math.roundToInt

/**
 * A horizontal slider with customizable appearance and behavior.
 *
 * @param value The current value of the slider within the specified [valueRange].
 * @param onValueChange A callback invoked when the slider's value changes due to user interaction.
 *   The new value is passed as a parameter.
 * @param onValueChangeFinished A callback invoked when the user finishes interacting with the slider,
 *   typically when they release the drag or tap.
 * @param modifier Modifier to be applied to the slider.
 * @param trackHeight The height of the slider track. Defaults to `4.dp`.
 * @param thumbRadius The radius of the slider thumb. Defaults to `7.dp`.
 * @param inactiveColor The color of the inactive portion of the slider track. Defaults to `Color.Gray.copy(alpha = 0.5f)`.
 * @param activeColor The color of the active portion of the slider track and the step indicators (if enabled). Defaults to `Color.Red`.
 * @param thumbColor The color of the slider thumb. Defaults to [activeColor].
 * @param valueRange The range of values that the slider can represent. If `null`, defaults to `0f..1f`.
 * @param stepSize If not `null`, the slider will snap to discrete values with the given step size. This enables step indicators.
 * @param trackShape The shape of the slider track. Defaults to `RectangleShape`.
 * @param thumbShape The shape of the slider thumb. Defaults to `CircleShape`.
 */
@Composable
internal fun HorizontalBoxSlider(
    value: Float,
    onValueChange: (Float) -> Unit,
    onValueChangeFinished: () -> Unit,
    modifier: Modifier = Modifier,
    trackHeight: Dp = 4.dp,
    thumbRadius: Dp = 7.dp,
    inactiveColor: Color = Color.Gray.copy(alpha = 0.5f),
    activeColor: Color = Color.Red,
    thumbColor: Color = activeColor,
    valueRange: ClosedFloatingPointRange<Float>? = null,
    stepSize: Float? = null,
    trackShape: Shape = RectangleShape,
    thumbShape: Shape = CircleShape
) {
    var isDragging by remember { mutableStateOf(false) }
    val range = valueRange ?: 0f..1f
    val steps = stepSize?.let { ((range.endInclusive - range.start) / it).toInt() }

    /**
     * Calculates the actual value based on the raw value (0f..1f).
     * If [stepSize] is provided, it snaps the value to the nearest step.
     *
     * @param rawValue A value between 0f and 1f representing the position of the thumb.
     * @return The actual value within the [valueRange], potentially snapped to a step.
     */
    fun calculateValue(rawValue: Float): Float {
        val normalized = rawValue.coerceIn(0f, 1f)
        val scaledValue = range.start + normalized * (range.endInclusive - range.start)

        return stepSize?.let { step ->
            val stepsCount = ((range.endInclusive - range.start) / step).toInt()
            val stepIndex = (normalized * stepsCount).roundToInt().coerceIn(0, stepsCount)
            range.start + stepIndex * step
        } ?: scaledValue
    }

    var sliderWidth by remember { mutableIntStateOf(0) }
    val normalizedValue = (value - range.start) / (range.endInclusive - range.start)
    val thumbOffset = (normalizedValue * sliderWidth).coerceIn(0f, sliderWidth.toFloat())

    Box(
        modifier = modifier
            .height(thumbRadius * 2 + trackHeight)
            .onSizeChanged { size -> sliderWidth = size.width }
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragStart = { offset ->
                        isDragging = true
                        val rawValue = (offset.x / sliderWidth.toFloat())
                        onValueChange(calculateValue(rawValue))
                    },
                    onDrag = { change, _ ->
                        val rawValue = (change.position.x / sliderWidth.toFloat())
                        onValueChange(calculateValue(rawValue))
                    },
                    onDragEnd = {
                        isDragging = false
                        onValueChangeFinished()
                    }
                )
            }
            .pointerInput(Unit) {
                detectTapGestures { offset ->
                    if (!isDragging) {
                        val rawValue = (offset.x / sliderWidth.toFloat())
                        onValueChange(calculateValue(rawValue))
                        onValueChangeFinished()
                    }
                }
            }
    ) {
        // Inactive track
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(trackHeight)
                .align(Alignment.CenterStart)
                .clip(trackShape)
                .background(inactiveColor)
        )

        // Active track
        Box(
            modifier = Modifier
                .width(thumbOffset.toDp())
                .height(trackHeight)
                .align(Alignment.CenterStart)
                .clip(trackShape)
                .background(activeColor)
        )

        // Thumb
        Box(
            modifier = Modifier
                .size(thumbRadius * 2)
                .offset(x = thumbOffset.toDp() - thumbRadius)
                .align(Alignment.CenterStart)
                .clip(thumbShape)
                .background(thumbColor)
        )

        // Steps indicators
        steps?.let { stepCount ->
            for (i in 0..stepCount) {
                val pos = (i.toFloat() / stepCount) * sliderWidth.toFloat()
                Box(
                    modifier = Modifier
                        .size(4.dp)
                        .offset(x = pos.toDp() - 2.dp)
                        .align(Alignment.CenterStart)
                        .clip(CircleShape)
                        .background(if (i <= (normalizedValue * stepCount).roundToInt()) activeColor else inactiveColor)
                )
            }
        }
    }
}