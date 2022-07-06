package com.aesuriagasalazar.competenciadigitalespracticum3_2.domain

import androidx.annotation.RawRes

data class MessageIntroduction(
    val id: TopicSyllabusId,
    val title: String,
    @RawRes val lottieAnim: Int,
    val body: String,
)
