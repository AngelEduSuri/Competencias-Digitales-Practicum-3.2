package com.aesuriagasalazar.competenciadigitalespracticum3_2.ui.screens.testfinished

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.aesuriagasalazar.competenciadigitalespracticum3_2.R
import com.aesuriagasalazar.competenciadigitalespracticum3_2.ui.components.LottieAnimationApp
import com.aesuriagasalazar.competenciadigitalespracticum3_2.ui.components.SurfaceApp
import com.aesuriagasalazar.competenciadigitalespracticum3_2.ui.navigation.RoutesApp
import com.aesuriagasalazar.competenciadigitalespracticum3_2.ui.theme.AppTheme
import com.aesuriagasalazar.competenciadigitalespracticum3_2.ui.theme.customFontFamily

@Composable
fun TestFinishedScreen(
    viewModel: TestFinishedViewModel = hiltViewModel(),
    onBackScreen: () -> Unit,
    onTestScreen: (String) -> Unit
) {

    val uiState = viewModel.uiState.collectAsState().value

    BackHandler {
        navigateScreen(onBackScreen = onBackScreen)
    }

    SurfaceApp {
        Column {
            TestFinishedContentBody(
                modifier = Modifier.weight(weight = 0.7f),
                result = uiState.result,
                totalQuestions = uiState.totalQuestions
            )
            TestFinishedButtons(
                modifier = Modifier.weight(weight = 0.3f),
                onHomeScreen = { navigateScreen(onBackScreen = onBackScreen) },
                onTestScreen = { onTestScreen(RoutesApp.Test.route) }
            )
        }
    }
}

private fun navigateScreen(onBackScreen: () -> Unit) {
    onBackScreen()
}

@Composable
private fun TestFinishedContentBody(
    modifier: Modifier = Modifier,
    result: Int,
    totalQuestions: Int
) {

    val orientation = isOrientationPortrait()

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.test_finished_tittle),
            style = MaterialTheme.typography.h4.copy(fontFamily = customFontFamily),
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        LottieAnimationApp(
            modifier = Modifier.weight(weight = 1f),
            lottieAnim = R.raw.test_finished_animation,
            iterations = 1
        )
        Text(
            text = stringResource(R.string.point_tittle),
            style = MaterialTheme.typography.subtitle1.copy(fontSize = if (orientation) 34.sp else 22.sp)
        )
        Text(
            text = "$result / $totalQuestions",
            style = MaterialTheme.typography.h3.copy(fontSize = if (orientation) 64.sp else 30.sp)
        )
    }
}

@Composable
fun TestFinishedButtons(
    modifier: Modifier = Modifier,
    onHomeScreen: () -> Unit,
    onTestScreen: () -> Unit
) {
    Column(
        modifier = modifier
            .padding(horizontal = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        Button(modifier = Modifier.fillMaxWidth(), onClick = onTestScreen) {
            Text(text = stringResource(R.string.try_again_button))
        }
        Button(modifier = Modifier.fillMaxWidth(), onClick = onHomeScreen) {
            Text(text = stringResource(R.string.home_button))
        }
    }
}

@Composable
fun isOrientationPortrait(): Boolean {
    val orientation = LocalConfiguration.current.orientation
    return orientation == 1
}

@Preview(name = "Portrait")
@Composable
fun TestFinishedScreenPreviewPort() {
    AppTheme {
        TestFinishedScreen(onBackScreen = { /*TODO*/ }, onTestScreen = {})
    }
}

@Preview(name = "LandScape", device = Devices.AUTOMOTIVE_1024p, widthDp = 720, heightDp = 360)
@Composable
fun TestFinishedScreenPreviewLand() {
    AppTheme {
        TestFinishedScreen(onBackScreen = { /*TODO*/ }, onTestScreen = {})
    }
}


