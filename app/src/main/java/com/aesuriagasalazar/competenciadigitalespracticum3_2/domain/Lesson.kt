package com.aesuriagasalazar.competenciadigitalespracticum3_2.domain

import androidx.annotation.DrawableRes

data class Lesson(
    val id: TopicSyllabusId,
    @DrawableRes val image: Int,
    val title: String,
    val message: String,
)
