package ru.ymv.yvideolite.components.dialogs

import android.view.Gravity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import ru.ymv.yvideolite.components.slider.SliderSelectorRow
import ru.ymv.yvideolite.components.text.AutoSizeText
import ru.ymv.yvideolite.utils.composable.DialogWindowSetup

/**
 * A dialog for selecting the playback speed.
 *
 * This composable function displays a dialog that allows the user to select the playback speed
 * of a media player. It includes a slider for fine-grained control and a set of buttons for
 * quickly selecting predefined speed variants.
 *
 * @param onDismiss A callback invoked when the dialog is dismissed.
 * @param onSelect A callback invoked when a speed is selected. The selected speed is passed as a parameter.
 * @param gravity The gravity of the dialog window within its container. Defaults to [Gravity.CENTER].
 * @param currentSpeed The currently selected playback speed.
 * @param speedVariants A list of predefined speed variants to display as buttons.
 * @param speedRange The range of possible playback speeds. Defaults to the range defined by the first and last elements of [speedVariants].
 * @param modifier Modifier to be applied to the dialog.
 * @param buttonsWidth The width of the speed variant buttons. Defaults to 77.dp.
 * @param buttonsHeight The height of the speed variant buttons. Defaults to 55.dp.
 * @param backgroundColor The background color of the dialog. Defaults to [Color.Unspecified].
 * @param shape The shape of the dialog. Defaults to a rounded corner shape with a radius of 15.dp.
 * @param textColor The color of the text. Defaults to [Color.Unspecified].
 * @param selectedColor The color of the text for the selected speed. Defaults to [textColor].
 * @param fontWeight The font weight of the text. Defaults to [FontWeight.Medium].
 * @param selectedFontWeight The font weight of the text for the selected speed. Defaults to [FontWeight.ExtraBold].
 * @param usePlatformDefaultWidth Whether to use the platform's default width for the dialog. Defaults to true.
 */
@Composable
fun SpeedSelectionDialog(
    onDismiss: () -> Unit,
    onSelect: (Float) -> Unit,
    gravity: Int = Gravity.CENTER,
    currentSpeed: Float,
    speedVariants: List<Float>,
    speedRange: ClosedFloatingPointRange<Float> = speedVariants.first()..speedVariants.last(),
    modifier: Modifier = Modifier,
    buttonsWidth: Dp = 77.dp,
    buttonsHeight: Dp = 55.dp,
    backgroundColor: Color = Color.Unspecified,
    shape: Shape = RoundedCornerShape(15.dp),
    textColor: Color = Color.Unspecified,
    selectedColor: Color = textColor,
    fontWeight: FontWeight = FontWeight.Medium,
    selectedFontWeight: FontWeight = FontWeight.ExtraBold,
    usePlatformDefaultWidth: Boolean = true
) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = usePlatformDefaultWidth),
    ) {
        DialogWindowSetup(gravity = gravity)
        Box(
            modifier = modifier.background(color = backgroundColor, shape = shape)
        ) {
            Column(modifier = Modifier.padding(vertical = 16.dp)) {
                var speedValue by remember { mutableFloatStateOf(currentSpeed) }

                Text(
                    text = "%.2fx".format(speedValue),
                    modifier = Modifier.fillMaxWidth(),
                    color = textColor,
                    textAlign = TextAlign.Center,
                    fontSize = 24.sp
                )

                Spacer(modifier = Modifier.height(16.dp))

                SliderSelectorRow(
                    onValueChange = { speedValue = it },
                    onSelect = { onSelect(speedValue) },
                    currentValue = speedValue,
                    range = speedRange,
                    modifier = Modifier.fillMaxWidth(),
                    contentColor = textColor
                )

                Spacer(modifier = Modifier.height(8.dp))


                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    speedVariants.forEach { speed ->
                        TextButton(
                            onClick = {
                                speedValue = speed
                                onSelect(speed)
                                onDismiss()
                            },
                            modifier = Modifier.size(buttonsWidth, buttonsHeight)
                        ) {
                            val fontDecor = if (speed == speedValue) {
                                selectedFontWeight to selectedColor
                            } else {
                                fontWeight to textColor
                            }
                            AutoSizeText(
                                text = "%.2fx".format(speed),
                                fontWeight = fontDecor.first,
                                color = fontDecor.second,
                            )
                        }
                    }
                }
            }
        }
    }
}