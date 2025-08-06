package ru.ymv.yvideolite.screens

import android.net.Uri
import androidx.annotation.OptIn
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.util.Log
import androidx.media3.common.util.UnstableApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class YVideoViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    val player: Player,
) : ViewModel() {

    private val videoUris = savedStateHandle.getStateFlow("video_uri", emptyList<Uri>())

    private val videoItems = videoUris.map { uris ->
        uris.map { uri ->
            MediaItem.fromUri(uri)
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = emptyList()
    )

    init {
        val videos = videoItems.value
        player.prepare()
        if (videos.isNotEmpty()) {
            player.addMediaItems(videos)
            playLastVideo()
        }
    }

    @OptIn(UnstableApi::class)
    private fun addVideoUri(uri: Uri) {
        player.addMediaItem(MediaItem.fromUri(uri))
        savedStateHandle["video_uri"] = videoUris.value + uri
        playLastVideo()
        Log.d("mylog", "addVideoUri ${videoUris.value}")
    }

    private fun playLastVideo() {
        player.seekTo(player.mediaItemCount.dec(), 0)
        player.playWhenReady = true
    }

    fun onEvent(event: ExoPlayerEvent) {
        when (event) {
            is ExoPlayerEvent.AddUri -> if (!videoUris.value.contains(event.uri)) addVideoUri(event.uri)
        }

    }

    override fun onCleared() {
        super.onCleared()
        player.release()
    }
}

sealed interface ExoPlayerEvent {
    data class AddUri(val uri: Uri) : ExoPlayerEvent
}