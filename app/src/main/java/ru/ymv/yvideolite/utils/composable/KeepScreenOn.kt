package ru.ymv.yvideolite.utils.composable

import android.app.Activity
import android.view.WindowManager
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalContext

/**
 * A composable that keeps the screen on while it is active.
 *
 * This composable uses the `FLAG_KEEP_SCREEN_ON` window flag to prevent the screen from dimming
 * or turning off while the composable is part of the composition.  It is designed for use within
 * an [Activity] context.  If used outside of an Activity, it will likely have no effect.
 *
 * @param shouldKeepScreenOn Whether the screen should be kept on. Defaults to `true`.
 */
@Composable
fun KeepScreenOn(shouldKeepScreenOn: Boolean = true) {
    val context = LocalContext.current
    val window = (context as? Activity)?.window

    DisposableEffect(shouldKeepScreenOn) {
        if (shouldKeepScreenOn) {
            window?.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        } else {
            window?.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        }
        onDispose {
            window?.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        }
    }
}