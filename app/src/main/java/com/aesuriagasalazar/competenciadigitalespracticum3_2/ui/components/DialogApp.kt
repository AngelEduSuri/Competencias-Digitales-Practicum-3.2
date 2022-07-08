package com.aesuriagasalazar.competenciadigitalespracticum3_2.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun DialogApp(
    titleBar: String,
    messageBody: String,
    titleButtonAccept: String,
    dialogSize: Dp,
    titleBackgroundColor: Color = MaterialTheme.colors.secondary,
    lottieAnimationBody: Int,
    onCloseDialog: () -> Unit
) {

    Dialog(onDismissRequest = onCloseDialog) {
        Card(
            backgroundColor = MaterialTheme.colors.background,
            modifier = Modifier.size(size = dialogSize),
            shape = MaterialTheme.shapes.small.copy(all = CornerSize(size = 8.dp))
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceAround
            ) {
                Column(modifier = Modifier.weight(weight = 0.7f)) {
                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        color = titleBackgroundColor
                    ) {
                        Text(
                            modifier = Modifier.padding(all = 8.dp),
                            text = titleBar,
                            style = MaterialTheme.typography.h5,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                    }
                    Text(
                        modifier = Modifier.padding(all = 8.dp),
                        text = messageBody,
                        style = MaterialTheme.typography.body1,
                        textAlign = TextAlign.Justify
                    )
                }
                LottieAnimationApp(
                    modifier = Modifier.weight(weight = 1f),
                    lottieAnim = lottieAnimationBody,
                )
                Box(modifier = Modifier.weight(weight = 0.3f)) {
                    Button(onClick = onCloseDialog) {
                        Text(text = titleButtonAccept)
                    }
                }
            }
        }
    }
}