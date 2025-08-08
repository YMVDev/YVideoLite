package ru.ymv.yvideolite.utils.extensions

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role

/**
 * Makes a composable clickable without displaying a ripple effect.
 *
 * This modifier enhances the standard [clickable] modifier by disabling the ripple effect. This is
 * achieved by providing a [MutableInteractionSource] with a `null` [androidx.compose.foundation.indication].
 * This modifier is useful when you want to provide a clickable area but do not want
 * the default ripple effect to be displayed on user interaction.
 *
 * @param enabled Controls the enabled state of the clickable. When `false`, the composable is not clickable.
 * @param onClickLabel Semantic label for the click action.
 * @param role The semantic [Role] for the clickable.
 * @param onClick The callback to be invoked when the composable is clicked.
 *
 * @return A [Modifier] that makes the composable clickable without a ripple effect.
 */
@Composable
fun Modifier.clickableWithoutRipple(
    enabled: Boolean = true,
    onClickLabel: String? = null,
    role: Role? = null,
    onClick: () -> Unit
): Modifier = clickable(
    interactionSource = remember { MutableInteractionSource() },
    indication = null,
    enabled = enabled,
    onClickLabel = onClickLabel,
    role = role,
    onClick = onClick
)