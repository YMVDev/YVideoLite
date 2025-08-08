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

/**
 * A Composable that displays an ExoPlayer [Player] on a surface.
 *
 * This composable function renders an ExoPlayer [Player] onto a surface, allowing you to display video
 * content. It provides customization options for the content scale and click handling.
 *
 * @param player The ExoPlayer [Player] to display.
 * @param modifier Modifier to be applied to the surface.
 * @param contentScale The [ContentScale] to use for resizing the video content. Defaults to [ContentScale.Fit].
 * @param onClick A callback invoked when the surface is clicked.
 * @param overlayContent A composable function that allows you to draw content on top of the video surface.
 */
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