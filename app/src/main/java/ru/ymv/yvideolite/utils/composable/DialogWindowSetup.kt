package ru.ymv.yvideolite.utils.composable

import android.view.Gravity
import android.view.WindowManager
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.window.DialogWindowProvider

@Composable
fun DialogWindowSetup(
    gravity: Int = Gravity.CENTER,
    dimAmount: Float = 0f,
    size: DialogWindowSize? = null,
    animationStyle: Int? = null,
) {
    val window = (LocalView.current.parent as? DialogWindowProvider)?.window ?: return
    val shouldDim = dimAmount > 0f && dimAmount <= 1f
    window.apply {
        setGravity(gravity)
        if (!shouldDim) {
            clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
            setDimAmount(0f)
        } else {
            setDimAmount(dimAmount.coerceIn(0f, 1f))
        }
        size?.let { setLayout(it.width, it.height) }
        animationStyle?.let { setWindowAnimations(it) }
    }
}

data class DialogWindowSize(
    val width: Int,
    val height: Int,
)
