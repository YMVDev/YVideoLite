package ru.ymv.yvideolite.utils.composable

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner

/**
 * Remembers and observes the lifecycle state of a [LifecycleOwner].
 *
 * This composable function creates a state variable that reflects the current lifecycle event
 * of the provided [LifecycleOwner]. It uses [DisposableEffect] to add and remove a
 * [LifecycleEventObserver] to the lifecycle, ensuring that the state is updated whenever the
 * lifecycle changes. The state is also saved using [rememberSaveable] to survive configuration changes.
 *
 * @param lifecycleOwner The [LifecycleOwner] to observe (e.g., an Activity or Fragment).
 * @return A [Lifecycle.Event] representing the current lifecycle event.
 */
@Composable
fun rememberPlayerLifecycleState(lifecycleOwner: LifecycleOwner): Lifecycle.Event {
    var lifecycle by rememberSaveable { mutableStateOf(Lifecycle.Event.ON_CREATE) }
    
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            lifecycle = event
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }
    
    return lifecycle
}