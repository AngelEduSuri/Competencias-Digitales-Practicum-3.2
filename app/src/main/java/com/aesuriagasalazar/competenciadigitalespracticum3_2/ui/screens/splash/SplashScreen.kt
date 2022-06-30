package com.aesuriagasalazar.competenciadigitalespracticum3_2.ui.screens.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.aesuriagasalazar.competenciadigitalespracticum3_2.R
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    onNextScreen: (String, String) -> Unit,
    viewModel: SplashScreenViewModel = hiltViewModel()
) {

    Surface(color = MaterialTheme.colors.primary) {
        Column {
            SplashIconApp(modifier = Modifier.weight(0.5f))
            SplashAnimationLottie(
                modifier = Modifier.weight(0.5f),
            )
        }
    }

    LaunchedEffect(true) {
        delay(2500)
        viewModel.onAppStart(onNextScreen)
    }
}

@Composable
fun SplashIconApp(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.icon_slash),
            contentDescription = stringResource(
                R.string.icon_splash_description
            )
        )
    }
}

@Composable
fun SplashAnimationLottie(
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier.fillMaxWidth()) {
        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.loading_splash_screen))
        LottieAnimation(composition = composition)
    }
}