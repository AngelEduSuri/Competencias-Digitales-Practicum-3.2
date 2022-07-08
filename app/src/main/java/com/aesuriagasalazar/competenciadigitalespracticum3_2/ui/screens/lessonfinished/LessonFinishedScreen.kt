package com.aesuriagasalazar.competenciadigitalespracticum3_2.ui.screens.lessonfinished

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.aesuriagasalazar.competenciadigitalespracticum3_2.R
import com.aesuriagasalazar.competenciadigitalespracticum3_2.ui.components.LottieAnimationApp
import com.aesuriagasalazar.competenciadigitalespracticum3_2.ui.components.SurfaceApp

@Composable
fun LessonFinishedScreen(
    viewModel: LessonFinishedViewModel = hiltViewModel(),
    onBackScreen: () -> Unit
) {

    val uiState = viewModel.uiState.collectAsState().value

    SurfaceApp {
        Column(
            modifier = Modifier.padding(all = 8.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LottieAnimationApp(
                modifier = Modifier.weight(weight = 1f),
                lottieAnim = R.raw.lesson_completed_animation,
                iterations = 1,
            )
            Column(
                modifier = Modifier.weight(weight = 1f),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = uiState.countLesson,
                    style = MaterialTheme.typography.h3.copy(fontSize = 70.sp),
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = stringResource(id = R.string.lesson_finished_title),
                    style = MaterialTheme.typography.h4,
                    fontWeight = FontWeight.Bold
                )
            }
            if (uiState.buttonVisible) {
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = onBackScreen
                ) {
                    Text(text = stringResource(id = R.string.home))
                }
            }
        }
    }
}