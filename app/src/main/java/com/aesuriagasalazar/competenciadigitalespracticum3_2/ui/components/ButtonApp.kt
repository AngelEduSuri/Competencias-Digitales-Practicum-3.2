package com.aesuriagasalazar.competenciadigitalespracticum3_2.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.aesuriagasalazar.competenciadigitalespracticum3_2.R

@Composable
fun ButtonApp(
    spacer: Dp = 8.dp,
    textButtonId: Int,
    imageId: Int,
    onClick: () -> Unit
) {
    Button(onClick = onClick) {
        Image(
            painter = painterResource(id = imageId),
            contentDescription = stringResource(
                R.string.login_google
            )
        )
        Spacer(modifier = Modifier.width(spacer))
        Text(text = stringResource(id = textButtonId))
    }
}