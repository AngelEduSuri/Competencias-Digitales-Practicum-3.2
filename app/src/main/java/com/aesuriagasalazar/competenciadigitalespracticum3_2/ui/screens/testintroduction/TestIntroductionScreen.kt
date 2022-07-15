package com.aesuriagasalazar.competenciadigitalespracticum3_2.ui.screens.testintroduction

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aesuriagasalazar.competenciadigitalespracticum3_2.R
import com.aesuriagasalazar.competenciadigitalespracticum3_2.ui.components.LottieAnimationApp
import com.aesuriagasalazar.competenciadigitalespracticum3_2.ui.components.SurfaceApp
import com.aesuriagasalazar.competenciadigitalespracticum3_2.ui.components.TopBarApplication
import com.aesuriagasalazar.competenciadigitalespracticum3_2.ui.navigation.RoutesApp

@Composable
fun TestIntroductionScreen(
    onBackScreen: () -> Unit,
    onStartTest: (String, String) -> Unit
) {

    val orientation = LocalConfiguration.current.orientation

    SurfaceApp {
        Column {
            TopBarApplication(
                title = stringResource(R.string.test_tittle),
                contentDescriptionNav = stringResource(id = R.string.back_screen),
                onBackPressed = onBackScreen
            )
            if (orientation == 2) {
                BodyHorizontalScreen(onStartTest = {
                    onStartTest(
                        RoutesApp.Test.route,
                        RoutesApp.MenuApp.route
                    )
                })
            } else {
                BodyVerticalScreen(onStartTest = {
                    onStartTest(
                        RoutesApp.Test.route,
                        RoutesApp.MenuApp.route
                    )
                })
            }
        }
    }
}

@Composable
private fun BodyVerticalScreen(onStartTest: () -> Unit) {
    Column {
        ContentBody(
            modifier = Modifier.weight(1f),
            onStartTest = onStartTest
        )
    }
}

@Composable
fun BodyHorizontalScreen(onStartTest: () -> Unit) {
    Row {
        ContentBody(
            modifier = Modifier.weight(1f),
            onStartTest = onStartTest
        )
    }
}

@Composable
private fun ContentBody(
    modifier: Modifier = Modifier,
    onStartTest: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(all = 16.dp)
    ) {
        CardTestIntroduction(onClick = { onStartTest() })
    }

    LottieAnimationApp(
        modifier = modifier,
        lottieAnim = R.raw.test_animation
    )
}

@Composable
private fun CardTestIntroduction(onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxSize(),
        elevation = 8.dp,
        shape = MaterialTheme.shapes.small.copy(all = CornerSize(size = 8.dp)),
        backgroundColor = MaterialTheme.colors.secondary
    ) {
        Column(
            modifier = Modifier.padding(all = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Text(
                text = stringResource(R.string.test_message_title),
                style = MaterialTheme.typography.h4,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Text(
                text = stringResource(R.string.test_message),
                style = MaterialTheme.typography.subtitle1,
                textAlign = TextAlign.Justify
            )
            Text(
                text = stringResource(R.string.test_start_title),
                style = MaterialTheme.typography.body2.copy(fontSize = 20.sp),
                fontWeight = FontWeight.Bold
            )
            Button(onClick = onClick) {
                Text(text = stringResource(id = R.string.start_title))
            }
        }
    }
}