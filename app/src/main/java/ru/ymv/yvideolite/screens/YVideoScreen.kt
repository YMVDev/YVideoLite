package ru.ymv.yvideolite.screens

import androidx.annotation.OptIn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.media3.common.Player
import androidx.media3.common.util.Log
import androidx.media3.common.util.UnstableApi
import ru.ymv.yvideolite.components.controls_rows.MainControlsRow
import ru.ymv.yvideolite.components.controls_rows.PlaybackControlsRow
import ru.ymv.yvideolite.components.surface.ExoPlayerSurface
import ru.ymv.yvideolite.components.time_bar.SimpleTimeBar
import ru.ymv.yvideolite.utils.composable.HandlePlayerLifecycle
import ru.ymv.yvideolite.utils.composable.KeepScreenOn
import ru.ymv.yvideolite.utils.composable.rememberContentScaleSwitcher
import ru.ymv.yvideolite.utils.composable.rememberPlayerLifecycleState
import ru.ymv.yvideolite.utils.composable.rememberPlayingState
import ru.ymv.yvideolite.utils.composable.rememberVideoSelector

@OptIn(UnstableApi::class)
@Composable
fun YVideoScreen(
    player: Player,
    onEvent: (ExoPlayerEvent) -> Unit
) {
    val isVideoLoaded by remember(player.duration) { derivedStateOf { player.duration > 0 } }
    var showControls by rememberSaveable { mutableStateOf(false) }
    val selectVideo = rememberVideoSelector { onEvent(ExoPlayerEvent.AddUri(it)) }
    val (contentScale, switchScale) = rememberContentScaleSwitcher()
    val lifecycleOwner = LocalLifecycleOwner.current
    val lifecycle = rememberPlayerLifecycleState(lifecycleOwner)
    val isPlaying = rememberPlayingState(player)

    HandlePlayerLifecycle(
        player = player,
        lifecycle = lifecycle,
        isPlaying = isPlaying,
    )

    KeepScreenOn(shouldKeepScreenOn = isPlaying)

    ExoPlayerSurface(
        player = player,
        contentScale = contentScale,
        modifier = Modifier.fillMaxSize(),
        onClick = { showControls = !showControls }
    ) {
        if (!isVideoLoaded) {
            StartScreen(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(40.dp)
            ) { selectVideo("video/mp4") }
        }
        if (isVideoLoaded && showControls) {
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .background(Color.Black.copy(alpha = 0.5f))
            ) {

                PlaybackControlsRow(
                    player = player,
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.Center)
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                ) {
                    SimpleTimeBar(
                        player = player,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                    )
                    MainControlsRow(
                        player = player,
                        onSelect = {
                            selectVideo("video/mp4")
                            showControls = false
                        },
                        onScale = switchScale,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.End,
                        selectedSpeedColor = Color.Cyan
                    )
                }
            }
        }
        LaunchedEffect(key1 = isPlaying) { Log.d("mylog", "isPlaying = $isPlaying") }
    }
}