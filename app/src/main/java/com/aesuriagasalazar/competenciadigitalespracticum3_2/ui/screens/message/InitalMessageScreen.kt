package com.aesuriagasalazar.competenciadigitalespracticum3_2.ui.screens.message

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Checkbox
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.aesuriagasalazar.competenciadigitalespracticum3_2.R
import com.aesuriagasalazar.competenciadigitalespracticum3_2.model.Message
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun InitialMessageScreen(
    onNextButtonClick: (String, String) -> Unit,
    viewModel: InitialMessageViewModel = hiltViewModel(),
) {

    val messageInitial = viewModel.uiState.collectAsState().value

    ScreenBody(
        messageInitial = messageInitial,
        onCheckedChanged = viewModel::onCheckChanged,
        onNextButton = { viewModel.onSaveCheckAndNavigate(onMenuScreen = onNextButtonClick) }
    )
}

@Composable
fun ScreenBody(
    messageInitial: InitialMessageUiState,
    onCheckedChanged: (Boolean) -> Unit,
    onNextButton: () -> Unit
) {

    Column(modifier = Modifier.fillMaxSize()) {
        AnimationInLottie(modifier = Modifier.weight(1f), messageInitial.message.lottieAnim)
        MessageContent(modifier = Modifier.weight(0.7f), messageInitial.message)
        ButtonActions(
            modifier = Modifier.weight(0.3f),
            messageInitial.isChecked,
            onCheckedChanged = onCheckedChanged,
            onClick = onNextButton
        )
    }
}

@Composable
fun AnimationInLottie(modifier: Modifier = Modifier, lottieAnim: Int) {
    Box(modifier = modifier) {
        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(lottieAnim))
        LottieAnimation(composition = composition, iterations = LottieConstants.IterateForever)
    }
}

@Composable
fun MessageContent(modifier: Modifier = Modifier, message: Message) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(4.dp),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = message.title,
            style = MaterialTheme.typography.h5,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Text(
            text = message.body,
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