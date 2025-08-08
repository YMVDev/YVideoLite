package ru.ymv.yvideolite.utils.composable

import android.view.Gravity
import android.view.WindowManager
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.window.DialogWindowProvider

/**
 * Configures the properties of the dialog window associated with the current Composable.
 *
 * This composable allows you to customize the appearance and behavior of a dialog window,
 * such as its gravity, dimming effect, size, and animation style. It retrieves the
 * `Window` object from the parent `DialogWindowProvider` (which is typically a `Dialog`)
 * and applies the specified settings.
 *
 * @param gravity The gravity of the dialog window within its container.  Defaults to [Gravity.CENTER].  See [Gravity] constants.
 * @param dimAmount The amount to dim the area behind the dialog window. A value of 0f means no dimming, and 1f means full dimming. Defaults to `0f`.
 *   Values outside the range of 0f to 1f are coerced into this range. Dimming will only be applied if value is greater than 0f.
 * @param size The desired size of the dialog window, specified as a [DialogWindowSize] object. If `null`, the size is not changed.
 * @param animationStyle The resource ID of the animation style to apply to the dialog window. If `null`, no animation is applied.
 *
 * @return Void. This function operates as a side effect to modify Dialog's window properties.
 *
 * @throws IllegalStateException if used outside of a [androidx.compose.ui.window.Dialog] composable
 */
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

/**
 * Represents the size of a dialog window using [width] and [height].
 * Note: You can use [WindowManager.LayoutParams.WRAP_CONTENT] or [WindowManager.LayoutParams.MATCH_PARENT] for special behavior.
 */
data class DialogWindowSize(
    val width: Int,
    val height: Int,
)

