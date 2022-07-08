package com.aesuriagasalazar.competenciadigitalespracticum3_2.ui.components

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.*
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

@Composable
fun LottieAnimationApp(
    modifier: Modifier = Modifier,
    lottieAnim: Int,
    iterations: Int = LottieConstants.IterateForever,
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(lottieAnim))

    Box(modifier = modifier) {
        LottieAnimation(
            composition = composition,
            iterations = iterations
        )
    }

}