package com.aesuriagasalazar.competenciadigitalespracticum3_2.domain

import androidx.annotation.RawRes

data class TopicMessageIntro(
    val id: TopicSyllabusId,
    val title: String,
    @RawRes val lottieAnim: Int,
    val body: String,
)
