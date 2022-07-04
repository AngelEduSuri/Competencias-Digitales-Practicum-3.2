package com.aesuriagasalazar.competenciadigitalespracticum3_2.ui.screens.components

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun LottieAnimationApp(modifier: Modifier = Modifier, lottieAnim: Int) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(lottieAnim))
    Box(modifier = modifier) {
        LottieAnimation(
            composition = composition,
            iterations = LottieConstants.IterateForever
        )
    }
}