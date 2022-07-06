package com.aesuriagasalazar.competenciadigitalespracticum3_2.ui.screens.lesson

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.aesuriagasalazar.competenciadigitalespracticum3_2.R
import com.aesuriagasalazar.competenciadigitalespracticum3_2.domain.Lesson
import com.aesuriagasalazar.competenciadigitalespracticum3_2.ui.components.SurfaceApp
import com.aesuriagasalazar.competenciadigitalespracticum3_2.ui.components.TopBarApplication
import com.aesuriagasalazar.competenciadigitalespracticum3_2.ui.theme.AppTheme
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun LessonScreen(
    viewModel: LessonViewModel = hiltViewModel(),
    onBackPressed: () -> Unit,
    onLessonFinished: (String, String) -> Unit
) {

    val systemUiController = rememberSystemUiController()
    val lifecycle = LocalLifecycleOwner.current
    val newBarColor = MaterialTheme.colors.secondary
    val oldBarColor = MaterialTheme.colors.primary
    val uiState = viewModel.uiState.collectAsState().value

    DisposableEffect(lifecycle) {
        val observer = composeLifeCycleBarColor(
            systemUiController = systemUiController,
            newBarColor = newBarColor,
            oldBarColor = oldBarColor
        )
        lifecycle.lifecycle.addObserver(observer)
        onDispose {
            lifecycle.lifecycle.removeObserver(observer)
        }
    }

    SurfaceApp {
        Column {
            TopBarLesson(
                title = uiState.titleBar,
                newBarColor = newBarColor,
                onBackPressed = onBackPressed
            )
            BodyContent(
                currentLesson = uiState.currentLesson,
                modifier = Modifier.weight(weight = 1f)
            )
            ButtonActions(
                lessonIndex = uiState.lessonIndex,
                onBackLesson = viewModel::onBackLesson,
                onNextLesson = {
                    viewModel.onNextLesson { route, popUp ->
                        onLessonFinished(route, popUp)
                    }
                }
            )
        }
    }
}

@Composable
private fun TopBarLesson(
    title: String,
    newBarColor: Color,
    onBackPressed: () -> Unit
) {
    TopBarApplication(
        title = title,
        contentDescriptionNav = stringResource(id = R.string.back_screen),
        onBackPressed = onBackPressed,
        backGroundColor = newBarColor
    )
}

@Composable
private fun BodyContent(
    currentLesson: Lesson,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .fillMaxSize()
                .weight(weight = 1f)
                .background(color = MaterialTheme.colors.secondary)
                .padding(all = 8.dp),
            painter = painterResource(id = currentLesson.image),
            contentDescription = currentLesson.title
        )
        Column(
            modifier = Modifier
                .weight(weight = 1f)
                .padding(all = 8.dp),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = currentLesson.title,
                style = MaterialTheme.typography.h5,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = currentLesson.message,
                style = MaterialTheme.typography.body1,
                textAlign = TextAlign.Justify
            )
        }
    }
}

@Composable
private fun ButtonActions(
    lessonIndex: Int,
    onBackLesson: () -> Unit,
    onNextLesson: () -> Unit
) {

    Row(
        modifier = Modifier
            .padding(all = 4.dp)
    ) {
        AnimatedVisibility(
            visible = lessonIndex != 0,
            modifier = Modifier.weight(1f)
        ) {
            Button(
                onClick = onBackLesson,
            ) {
                Text(text = stringResource(id = R.string.back_lesson))
            }
        }

        Spacer(modifier = Modifier.width(width = 4.dp))
        Button(
            onClick = onNextLesson,
            modifier = Modifier
                .weight(weight = 1f)
                .animateContentSize()
        ) {
            Text(text = stringResource(id = R.string.next_lesson))
        }
    }
}

private fun composeLifeCycleBarColor(
    systemUiController: SystemUiController,
    newBarColor: Color,
    oldBarColor: Color
) = LifecycleEventObserver { _, event ->
    if (event == Lifecycle.Event.ON_START) {
        systemUiController.setSystemBarsColor(
            color = newBarColor
        )
    } else if (event == Lifecycle.Event.ON_STOP) {
        systemUiController.setSystemBarsColor(
            color = oldBarColor
        )
    }
}

@Preview
@Composable
fun ButtonActionsPreview() {
    AppTheme {
        ButtonActions(lessonIndex = 0, onBackLesson = { }, onNextLesson = {})
    }
}
