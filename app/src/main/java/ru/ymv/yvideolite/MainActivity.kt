package ru.ymv.yvideolite

import android.app.ComponentCaller
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ru.ymv.yvideolite.screens.ExoPlayerEvent
import ru.ymv.yvideolite.screens.YVideoScreen
import ru.ymv.yvideolite.screens.YVideoViewModel
import ru.ymv.yvideolite.ui.theme.YVideoTheme
import ru.ymv.yvideolite.utils.extensions.SystemUiVisibility
import ru.ymv.yvideolite.utils.extensions.toggleSystemUiVisibility

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: YVideoViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        window.toggleSystemUiVisibility(SystemUiVisibility.Hide)
        val player = viewModel.player
        setContent {
            YVideoTheme {
                YVideoScreen(
                    player = player,
                    onEvent = viewModel::onEvent,
                )
            }
        }

        handleIntent(intent)
    }

    override fun onNewIntent(intent: Intent, caller: ComponentCaller) {
        super.onNewIntent(intent, caller)
        setIntent(intent)
        handleIntent(intent)
    }

    private fun handleIntent(intent: Intent?) {
        val uri = intent?.takeIf {
            it.action == Intent.ACTION_VIEW && it.type?.startsWith("video/") == true
        }?.data

        uri?.let { uri ->
            viewModel.onEvent(ExoPlayerEvent.AddUri(uri))
        }
    }
}