package ru.ymv.yvideolite.utils.extensions

import android.view.Window
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat

/**
 * Toggles the visibility of system UI elements (status bar and navigation bar).
 *
 * This function provides a convenient way to show or hide the system UI elements, such as the
 * status bar and navigation bar. It uses [WindowInsetsControllerCompat] to control the visibility
 * and behavior of these elements.
 *
 * **Important:** This function should only be called from within an [android.app.Activity] context.  Calling it
 * from other contexts may result in unexpected behavior or errors, as it relies on accessing the
 * `Window` associated with an Activity.
 *
 * @param visibility A [SystemUiVisibility] sealed class indicating whether to show or hide the system UI. Defaults to [SystemUiVisibility.Show].
 * @param systemBarsBehavior The behavior of the system bars when they are hidden. Defaults to
 *   [WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE], which allows the user to
 *   swipe to bring the bars back temporarily.
 */
fun Window.toggleSystemUiVisibility(
    visibility: SystemUiVisibility = SystemUiVisibility.Show,
    systemBarsBehavior: Int = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
) {
    val controller = WindowCompat.getInsetsController(this, this.decorView)
    controller.apply {
        this.systemBarsBehavior = systemBarsBehavior
        when (visibility) {
            is SystemUiVisibility.Show -> show(WindowInsetsCompat.Type.systemBars())
            is SystemUiVisibility.Hide -> hide(WindowInsetsCompat.Type.systemBars())
        }
    }
}

/**
 * Sealed class representing the desired visibility state of the system UI.
 */
sealed class SystemUiVisibility {
    /**
     * Show the system UI elements (status bar and navigation bar).
     */
    object Show : SystemUiVisibility()

    /**
     * Hide the system UI elements (status bar and navigation bar).
     */
    object Hide : SystemUiVisibility()
}