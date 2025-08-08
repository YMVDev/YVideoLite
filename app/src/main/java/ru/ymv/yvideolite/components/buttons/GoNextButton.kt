package ru.ymv.yvideolite.components.buttons

import androidx.annotation.OptIn
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SkipNext
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.ui.compose.state.rememberNextButtonState
import ru.ymv.yvideolite.R

/**
 * A button that allows the user to skip to the next item in a playlist or chapter.
 *
 * This composable function creates an IconButton that, when clicked, advances the ExoPlayer [Player]
 * to the next item. The button's appearance can be customized with different colors, icon sizes,
 * and icons.
 *
 * @param player The ExoPlayer [Player] to control.
 * @param modifier Modifier to be applied to the button.
 * @param color The color of the icon when the button is enabled. Defaults to [Color.Unspecified].
 * @param disabledColor The color of the icon when the button is disabled. Defaults to [Color.Unspecified].
 * @param backgroundColor The background color of the button. Defaults to [Color.Transparent].
 * @param iconSize The size of the icon. Defaults to 40.dp.
 * @param icon The [ImageVector] to use for the icon. Defaults to [Icons.Default] SkipNext.
 */
@OptIn(UnstableApi::class)
@Composable
internal fun GoNextButton(
    player: Player,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    disabledColor: Color = Color.Unspecified,
    backgroundColor: Color = Color.Transparent,
    iconSize: Dp = 40.dp,
    icon: ImageVector = Icons.Default.SkipNext
) {
    val state = rememberNextButtonState(player)
    IconButton(
        onClick = state::onClick,
        modifier = modifier,
        colors = IconButtonDefaults.filledIconButtonColors().copy(
            containerColor = backgroundColor,
            contentColor = color,
            disabledContainerColor = backgroundColor,
            disabledContentColor = disabledColor
        ),
        enabled = state.isEnabled
    ) {
        Icon(
            imageVector = icon,
            contentDescription = stringResource(R.string.Next),
            modifier = Modifier.size(iconSize)
        )
    }
}