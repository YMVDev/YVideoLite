package ru.ymv.yvideolite.components.buttons

import androidx.annotation.OptIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Repeat
import androidx.compose.material.icons.filled.RepeatOn
import androidx.compose.material.icons.filled.RepeatOneOn
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
import androidx.media3.ui.compose.state.rememberRepeatButtonState
import ru.ymv.yvideolite.R

@OptIn(UnstableApi::class)
@Composable
internal fun RepeatButton(
    player: Player,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    disabledColor: Color = Color.Unspecified,
    backgroundColor: Color = Color.Transparent,
    iconRepeat: ImageVector = Icons.Default.Repeat,
    iconRepeatOneOn: ImageVector = Icons.Default.RepeatOneOn,
    iconRepeatOn: ImageVector = Icons.Default.RepeatOn
) {
    val state = rememberRepeatButtonState(player)
    val iconProperties = when (state.repeatModeState) {
        Player.REPEAT_MODE_OFF -> iconRepeat to stringResource(R.string.Desc_repeat_off)
        Player.REPEAT_MODE_ONE -> iconRepeatOneOn to stringResource(R.string.Desc_repeat_one)
        else -> iconRepeatOn to stringResource(R.string.Desc_repeat_all)
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