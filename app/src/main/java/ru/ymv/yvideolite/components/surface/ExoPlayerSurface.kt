package ru.ymv.yvideolite.components.surface

import androidx.annotation.OptIn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.ui.compose.PlayerSurface
import androidx.media3.ui.compose.SURFACE_TYPE_SURFACE_VIEW
import androidx.media3.ui.compose.modifiers.resizeWithContentScale
import androidx.media3.ui.compose.state.rememberPresentationState
import ru.ymv.yvideolite.utils.extensions.clickableWithoutRipple

@OptIn(UnstableApi::class)
@Composable
fun ExoPlayerSurface(
    player: Player,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Fit,
    onClick: () -> Unit = {},
    overlayContent: @Composable BoxScope.() -> Unit = {}
) {
    val presentationState = rememberPresentationState(player)

    Box(
        modifier = modifier
            .clickableWithoutRipple(onClick = onClick)
            .background(Color.Black)
    ) {

        PlayerSurface(
            player = player,
            modifier = Modifier.resizeWithContentScale(contentScale, presentationState.videoSizeDp),
            surfaceType = SURFACE_TYPE_SURFACE_VIEW
        )
        if (presentationState.coverSurface) {
            Box(
                Modifier
                    .matchParentSize()
                    .background(Color.Black)
            )
        }
        overlayContent()
    }
}