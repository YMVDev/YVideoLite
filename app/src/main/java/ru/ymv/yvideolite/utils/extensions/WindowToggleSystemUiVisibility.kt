package ru.ymv.yvideolite.utils.extensions

import android.view.Window
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat

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

sealed class SystemUiVisibility {
    object Show : SystemUiVisibility()
    object Hide : SystemUiVisibility()
}