package ru.ymv.yvideolite.components.buttons

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FileOpen
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import ru.ymv.yvideolite.R

/**
 * A button that allows the user to select a video file.
 *
 * This composable function creates an IconButton that, when clicked, triggers a video selection
 * action, such as opening a file picker or displaying a video library.
 *
 * @param onClick A callback invoked when the button is clicked. This callback is typically used to
 *  initiate the video selection process.
 * @param modifier Modifier to be applied to the button.
 * @param enabled Whether the button is enabled. Defaults to true.
 * @param color The color of the icon when the button is enabled. Defaults to [Color.Unspecified].
 * @param disabledColor The color of the icon when the button is disabled. Defaults to [Color.Unspecified].
 * @param backgroundColor The background color of the button. Defaults to [Color.Transparent].
 * @param icon The [ImageVector] to use for the button's icon. Defaults to [Icons.Default] FileOpen.
 */
@Composable
internal fun SelectButton(
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    color: Color = Color.Unspecified,
    disabledColor: Color = Color.Unspecified,
    backgroundColor: Color = Color.Transparent,
    icon: ImageVector = Icons.Default.FileOpen,
) {
    IconButton(
        onClick = onClick,
        modifier = modifier,
        colors = IconButtonDefaults.filledIconButtonColors().copy(
            containerColor = backgroundColor,
            contentColor = color,
            disabledContainerColor = backgroundColor,
            disabledContentColor = disabledColor
        ),
        enabled = enabled
    ) {
        Icon(
            imageVector = icon,
            contentDescription = stringResource(R.string.Desc_select_video),
        )
    }
}