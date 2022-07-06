package com.aesuriagasalazar.competenciadigitalespracticum3_2.ui.components

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun TopBarApplication(
    title: String,
    elevation: Dp = 8.dp,
    iconNav: ImageVector = Icons.Default.ArrowBack,
    backGroundColor: Color = MaterialTheme.colors.primary,
    contentDescriptionNav: String?,
    onBackPressed: () -> Unit,
) {

    TopAppBar(
        elevation = elevation,
        title = {
            Text(
                text = title.ifEmpty { "" },
                style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.Bold
            )
        },
        navigationIcon = {
            IconButton(onClick = onBackPressed) {
                Icon(
                    imageVector = iconNav,
                    contentDescription = contentDescriptionNav
                )
            }
        },
        backgroundColor = backGroundColor
    )
}