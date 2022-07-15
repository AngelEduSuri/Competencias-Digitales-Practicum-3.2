package com.aesuriagasalazar.competenciadigitalespracticum3_2.ui.screens.test

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.aesuriagasalazar.competenciadigitalespracticum3_2.R
import com.aesuriagasalazar.competenciadigitalespracticum3_2.ui.components.SurfaceApp
import com.aesuriagasalazar.competenciadigitalespracticum3_2.ui.components.TopBarApplication
import com.aesuriagasalazar.competenciadigitalespracticum3_2.ui.theme.customFontFamily

@Composable
fun TestSyllabusScreen(
    viewModel: TestSyllabusViewModel = hiltViewModel(),
    onBackScreen: () -> Unit,
    onTestFinished: (String, String) -> Unit
) {

    val uiState = viewModel.uiState.collectAsState().value

    SurfaceApp {
        Column {
            TopBarApplication(
                title = stringResource(R.string.question_count, uiState.testIndex + 1),
                contentDescriptionNav = stringResource(id = R.string.back_screen),
                onBackPressed = onBackScreen
            )
            TestSyllabusBody(
                currentTest = uiState.currentTest,
                questionTitle = uiState.questionTitle,
                answer = uiState.answerSelected,
                questionCount = uiState.testIndex,
                points = uiState.points,
                isCorrectAnswer = uiState.isAnswerCorrect,
                isAnyAnswerSelected = uiState.isAnyAnswerSelected,
                totalQuestionsCount = uiState.totalQuestionsCount,
                isEnabled = uiState.enabledResponseButton,
                onAnswerSelected = viewModel::onAnswerSelected,
                onCheckAnswer = {
                    viewModel.onCheckAnswer { route, popUp ->
                        onTestFinished(route, popUp)
                    }
                }
            )
        }
    }
}

@Composable
private fun TestSyllabusBody(
    currentTest: List<String>,
    questionTitle: String,
    answer: String,
    questionCount: Int,
    totalQuestionsCount: Int,
    points: Int,
    isCorrectAnswer: Boolean?,
    isAnyAnswerSelected: Boolean,
    isEnabled: Boolean,
    onAnswerSelected: (String) -> Unit,
    onCheckAnswer: () -> Unit
) {
    Column(modifier = Modifier.padding(all = 16.dp)) {
        HeaderContent(
            questionCount = questionCount,
            totalQuestionsCount = totalQuestionsCount,
            points = points
        )
        Spacer(modifier = Modifier.height(height = 16.dp))
        QuestionContent(
            modifier = Modifier.weight(weight = 0.8f),
            questionTitle = questionTitle,
            currentTest = currentTest,
            answer = answer,
            onAnswerSelected = onAnswerSelected,
            isCorrectAnswer = isCorrectAnswer,
            isAnyAnswerSelected = isAnyAnswerSelected,
        )
        AnswerButtonAction(
            modifier = Modifier.weight(weight = 0.2f),
            onClick = onCheckAnswer,
            isEnabled = isEnabled,
        )
    }
}

@Composable
private fun HeaderContent(
    questionCount: Int,
    totalQuestionsCount: Int,
    points: Int
) {
    val animatedProgress by animateFloatAsState(
        targetValue = (questionCount + 1) / totalQuestionsCount.toFloat(),
        animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec
    )
    Column {
        LinearProgressIndicator(
            progress = animatedProgress,
            modifier = Modifier
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(height = 8.dp))
        Text(
            text = stringResource(R.string.points_correct, points),
            style = MaterialTheme.typography.subtitle1
        )
    }
}

@Composable
private fun QuestionContent(
    modifier: Modifier = Modifier,
    questionTitle: String,
    currentTest: List<String>,
    answer: String,
    isCorrectAnswer: Boolean?,
    isAnyAnswerSelected: Boolean,
    onAnswerSelected: (String) -> Unit
) {
    Column(modifier = modifier) {
        Text(
            modifier = Modifier.animateContentSize(),
            text = questionTitle,
            style = MaterialTheme.typography.subtitle1.copy(fontSize = 20.sp).merge(),
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(height = 16.dp))
        Card(elevation = 12.dp) {
            Column(modifier = Modifier.animateContentSize()) {
                currentTest.forEach {
                    LabelRadioButton(
                        question = it,
                        selected = it == answer,
                        onQuestionSelected = onAnswerSelected
                    )
                }
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(weight = 1f),
            contentAlignment = Alignment.BottomCenter
        ) {
            if (isAnyAnswerSelected) {
                Text(
                    text = stringResource(R.string.anwer_empty_context),
                    style = MaterialTheme.typography.subtitle1.copy(
                        fontSize = 22.sp,
                        letterSpacing = 2.sp,
                        fontFamily = customFontFamily
                    )
                )
            }
            isCorrectAnswer?.let {
                Text(
                    text = if (it) {
                        stringResource(R.string.answer_correct)
                    } else {
                        stringResource(R.string.answer_incorrect)
                    },
                    style = MaterialTheme.typography.subtitle1.copy(
                        fontSize = 22.sp,
                        letterSpacing = 2.sp,
                        fontFamily = customFontFamily
                    )
                )
            }
        }
    }
}

@Composable
private fun LabelRadioButton(
    question: String,
    selected: Boolean,
    onQuestionSelected: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .clickable { onQuestionSelected(question) }
            .fillMaxWidth()
            .padding(all = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(selected = selected, onClick = null)
        Spacer(modifier = Modifier.width(width = 8.dp))
        Text(
            text = question,
            style = MaterialTheme.typography.body1.merge()
        )
    }
}

@Composable
fun AnswerButtonAction(
    modifier: Modifier = Modifier,
    isEnabled: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        Button(modifier = Modifier.fillMaxWidth(), onClick = onClick, enabled = isEnabled) {
            Text(text = stringResource(R.string.response_title))
        }
    }
}