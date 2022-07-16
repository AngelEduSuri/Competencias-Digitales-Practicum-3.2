package com.aesuriagasalazar.competenciadigitalespracticum3_2.ui.screens.score

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.aesuriagasalazar.competenciadigitalespracticum3_2.R
import com.aesuriagasalazar.competenciadigitalespracticum3_2.domain.TestScore
import com.aesuriagasalazar.competenciadigitalespracticum3_2.ui.components.LottieAnimationApp
import com.aesuriagasalazar.competenciadigitalespracticum3_2.ui.components.SurfaceApp
import com.aesuriagasalazar.competenciadigitalespracticum3_2.ui.components.TopBarApplication
import com.aesuriagasalazar.competenciadigitalespracticum3_2.ui.theme.AppTheme

@Composable
fun ScoreScreen(
    viewModel: ScoreViewModel = hiltViewModel(),
    onBackScreen: () -> Unit
) {

    val uiState = viewModel.uiState.collectAsState().value

    SurfaceApp {
        Column {
            TopBarApplication(
                title = stringResource(id = R.string.my_result),
                contentDescriptionNav = stringResource(id = R.string.back_screen),
                onBackPressed = onBackScreen
            )
            ResultScreenBody(
                score = uiState.score,
                orderBy = uiState.orderBy,
                onOrderClick = viewModel::onScoreOrder
            )
        }
    }
}

@Composable
private fun ResultScreenBody(
    score: List<TestScore>,
    orderBy: Boolean,
    onOrderClick: () -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(weight = 0.4f)
                .background(MaterialTheme.colors.primary)
                .padding(all = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            LottieAnimationApp(
                lottieAnim = R.raw.my_test_score_animation
            )
        }
        Column(
            modifier = Modifier
                .weight(weight = 0.6f)
                .background(MaterialTheme.colors.secondaryVariant),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(height = 8.dp))
            Text(
                text = stringResource(id = R.string.score_title),
                style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.Bold
            )
            if (score.isEmpty()) {
                ContextIfScoreIsEmpty()
            } else {
                ScoreListContent(
                    score = score,
                    orderBy = orderBy,
                    onClick = onOrderClick
                )
            }
        }
    }
}

@Composable
private fun ContextIfScoreIsEmpty() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(id = R.string.score_empty_context),
            style = MaterialTheme.typography.subtitle1.copy(fontSize = 20.sp),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun ScoreListContent(
    score: List<TestScore>,
    orderBy: Boolean,
    onClick: () -> Unit
) {
    Column(modifier = Modifier.padding(16.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = stringResource(id = R.string.order_by))
            IconButton(onClick = onClick) {
                Icon(
                    imageVector = if (orderBy) Icons.Default.ArrowUpward else Icons.Default.ArrowDownward,
                    contentDescription = stringResource(id = R.string.order_by)
                )
            }
        }
        Surface(shape = MaterialTheme.shapes.small) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(all = 8.dp),
                verticalArrangement = Arrangement.spacedBy(space = 8.dp)
            ) {
                items(score) {
                    ItemScoreList(testScore = it)
                }
            }
        }
    }
}

@Composable
fun ItemScoreList(testScore: TestScore) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.surface)
            .height(height = 50.dp),
        backgroundColor = MaterialTheme.colors.secondary
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Text(
                text = stringResource(id = R.string.score, testScore.score),
                style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.width(width = 8.dp))
            Text(
                text = stringResource(id = R.string.date, testScore.date.split(" ")[0]),
                style = MaterialTheme.typography.subtitle1
            )
        }
    }
}

@Preview
@Composable
fun ItemScoreListPreview() {
    AppTheme {
        ItemScoreList(testScore = TestScore(14, "MM-dd-yyyy HH:mm:ss"))
    }
}