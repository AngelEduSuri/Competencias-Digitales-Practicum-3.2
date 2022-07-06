package com.aesuriagasalazar.competenciadigitalespracticum3_2.ui.screens.message

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.aesuriagasalazar.competenciadigitalespracticum3_2.R
import com.aesuriagasalazar.competenciadigitalespracticum3_2.ui.components.LottieAnimationApp
import com.aesuriagasalazar.competenciadigitalespracticum3_2.ui.components.SurfaceApp

@Composable
fun InitialMessageScreen(
    onNextButtonClick: (String, String) -> Unit,
    viewModel: InitialMessageViewModel = hiltViewModel(),
) {

    val messageInitial = viewModel.uiState.collectAsState().value

    SurfaceApp {
        ScreenBody(
            messageInitial = messageInitial,
            onCheckedChanged = viewModel::onCheckChanged,
            onNextButton = { viewModel.onSaveCheckAndNavigate(onMenuScreen = onNextButtonClick) }
        )
    }
}

@Composable
fun ScreenBody(
    messageInitial: InitialMessageUiState,
    onCheckedChanged: (Boolean) -> Unit,
    onNextButton: () -> Unit
) {

    Column(modifier = Modifier.fillMaxSize()) {
        LottieAnimationApp(
            modifier = Modifier.weight(1f),
            R.raw.initial_message_lottie
        )
        MessageContent(
            modifier = Modifier.weight(0.7f),
            title = stringResource(id = R.string.initial_message_title),
            message = stringResource(id = R.string.initial_message_body)
        )
        ButtonActions(
            modifier = Modifier.weight(0.3f),
            messageInitial.isChecked,
            onCheckedChanged = onCheckedChanged,
            onClick = onNextButton
        )
    }
}

@Composable
fun MessageContent(
    modifier: Modifier = Modifier,
    title: String,
    message: String
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(4.dp),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.h5,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Text(
            text = message,
            style = MaterialTheme.typography.body1,
            textAlign = TextAlign.Justify
        )
    }
}

@Composable
fun ButtonActions(
    modifier: Modifier = Modifier,
    checked: Boolean,
    onCheckedChanged: (Boolean) -> Unit,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(4.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            modifier = Modifier.weight(1f),
            onClick = onClick
        ) {
            Text(text = stringResource(R.string.next))
        }
        Checkbox(checked = checked, onCheckedChange = onCheckedChanged)
        Text(text = stringResource(R.string.not_show_message))
    }
}