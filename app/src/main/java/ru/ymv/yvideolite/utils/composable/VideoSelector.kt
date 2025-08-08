package ru.ymv.yvideolite.utils.composable

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

/**
 * A class that simplifies launching an activity to select a video.
 *
 * This class encapsulates the logic for launching an activity to pick a video from the device's storage.
 * It uses an [ActivityResultLauncher] to handle the activity result.
 *
 * @param launcher The [ActivityResultLauncher] used to launch the video selection activity.
 */
class VideoSelector (private val launcher: ActivityResultLauncher<String>) {
    /**
     * A class that simplifies launching an activity to select a video.
     *
     * This class encapsulates the logic for launching an activity to pick a video from the device's storage.
     * It uses an [ActivityResultLauncher] to handle the activity result.
     *
     * @param launcher The [ActivityResultLauncher] used to launch the video selection activity.
     */
    operator fun invoke(mimeType: String = "video/*") {
        launcher.launch(mimeType)
    }
}

/**
 * Creates and remembers a [VideoSelector] for selecting a video.
 *
 * This composable function creates a [VideoSelector] and ties its lifecycle to the composition.
 * It uses [rememberLauncherForActivityResult] to handle the video selection activity and its result.
 *
 * @param onError A callback invoked when an error occurs during video selection. The exception is passed as a parameter.
 * @param onVideoSelected A callback invoked when a video is successfully selected. The [Uri] of the selected video is passed as a parameter.
 * @return A [VideoSelector] instance.
 */
@Composable
fun rememberVideoSelector(
    onError: (Exception) -> Unit = {}, onVideoSelected: (Uri) -> Unit,
): VideoSelector {
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            uri?.let(onVideoSelected) ?: onError(IllegalArgumentException("Uri is null"))
        }
    )

    return remember { VideoSelector(launcher) }
}