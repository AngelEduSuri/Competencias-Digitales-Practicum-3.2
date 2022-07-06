package com.aesuriagasalazar.competenciadigitalespracticum3_2.ui.screens.introduction

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.aesuriagasalazar.competenciadigitalespracticum3_2.R
import com.aesuriagasalazar.competenciadigitalespracticum3_2.ui.components.LottieAnimationApp
import com.aesuriagasalazar.competenciadigitalespracticum3_2.ui.components.SurfaceApp
import com.aesuriagasalazar.competenciadigitalespracticum3_2.ui.components.TopBarApplication

@Composable
fun IntroductionScreen(
    viewModel: IntroductionViewModel = hiltViewModel(),
    onNavigate: (String?) -> Unit,
    onBackPressed: () -> Unit
) {

    val uiState = viewModel.uiState.collectAsState().value

    SurfaceApp {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            TopBarApplication(
                title = uiState.topic.title,
                contentDescriptionNav = stringResource(id = R.string.back_screen),
                onBackPressed = onBackPressed
            )

            Column(modifier = Modifier.padding(all = 4.dp)) {
                LottieAnimationApp(
                    lottieAnim = uiState.topic.lottieAnim,
                    modifier = Modifier.weight(weight = 0.6f)
                )

                Text(
                    modifier = Modifier.weight(weight = 0.4f),
                    text = uiState.topic.body,
                    style = MaterialTheme.typography.h6,
                    textAlign = TextAlign.Justify
                )

                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { viewModel.onNavigateScreen(onNavigate) }
                ) {
                    Text(text = stringResource(if (uiState.isContentEmpty) R.string.back_screen else R.string.start_lesson))
                }
            }
        }
    }
}

