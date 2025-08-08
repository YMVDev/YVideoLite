package ru.ymv.yvideolite.components.buttons

import androidx.annotation.OptIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Shuffle
import androidx.compose.material.icons.filled.ShuffleOn
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.ui.compose.state.rememberShuffleButtonState
import ru.ymv.yvideolite.R

/**
 * A button that toggles shuffle mode for an ExoPlayer [Player].
 *
 * This composable function creates an IconButton that, when clicked, toggles the shuffle mode of
 * the ExoPlayer. When shuffle mode is enabled, the player will play items in a random order.
 *
 * @param player The ExoPlayer [Player] to control.
 * @param modifier Modifier to be applied to the button.
 * @param color The color of the icon when the button is enabled. Defaults to [Color.Unspecified].
 * @param disabledColor The color of the icon when the button is disabled. Defaults to [Color.Unspecified].
 * @param backgroundColor The background color of the button. Defaults to [Color.Transparent].
 * @param iconShuffleOn The [ImageVector] to use for the icon when shuffle mode is enabled. Defaults to [Icons.Default] ShuffleOn.
 * @param iconShuffleOff The [ImageVector] to use for the icon when shuffle mode is disabled. Defaults to [Icons.Default] Shuffle.
 */
@OptIn(UnstableApi::class)
@Composable
internal fun ShuffleButton(
    player: Player,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    disabledColor: Color = Color.Unspecified,
    backgroundColor: Color = Color.Transparent,
    iconShuffleOn: ImageVector = Icons.Default.ShuffleOn,
    iconShuffleOff: ImageVector = Icons.Default.Shuffle
) {
    val state = rememberShuffleButtonState(player)
    val iconProperties = if (state.shuffleOn) {
        iconShuffleOn to stringResource(R.string.Desc_shuffle_on)
    } else {
        iconShuffleOff to stringResource(R.string.Desc_shuffle_off)
    }
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
            imageVector = iconProperties.first,
            contentDescription = iconProperties.second,
        )
    }
}