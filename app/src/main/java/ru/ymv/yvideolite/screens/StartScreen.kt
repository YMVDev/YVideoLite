package ru.ymv.yvideolite.screens

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import ru.ymv.yvideolite.R
import ru.ymv.yvideolite.components.buttons.BigCircularButton

@Composable
fun StartScreen(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    val configuration = LocalConfiguration.current
    val isPortrait = configuration.orientation == Configuration.ORIENTATION_PORTRAIT
    if (isPortrait) {
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Content(onClick = onClick)
        }
    } else {
        Row(
            modifier = modifier,
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Content(onClick = onClick)
        }
    }


}

@Composable
private fun Content(onClick: () -> Unit = {}) {
    Image(
        imageVector = ImageVector.vectorResource(id = R.drawable.yvideolite_icon),
        contentDescription = stringResource(R.string.Desc_application_icon),
        modifier = Modifier
            .size(300.dp)
    )
    Spacer(modifier = Modifier.size(40.dp))
    BigCircularButton(
        text = stringResource(R.string.Desc_select_video),
        borderColor = Color.White,
        onClick = onClick
    )
}