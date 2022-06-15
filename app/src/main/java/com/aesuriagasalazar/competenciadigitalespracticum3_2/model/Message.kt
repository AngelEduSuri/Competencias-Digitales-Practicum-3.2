package com.aesuriagasalazar.competenciadigitalespracticum3_2.model

import androidx.annotation.RawRes

data class Message(
    @RawRes val lottieAnim: Int,
    val title: String,
    val body: String,
)
