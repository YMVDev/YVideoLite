package ru.ymv.yvideolite.utils.composable

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

class VideoSelector (private val launcher: ActivityResultLauncher<String>) {
    operator fun invoke(mimeType: String = "video/*") {
        launcher.launch(mimeType)
    }
}

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