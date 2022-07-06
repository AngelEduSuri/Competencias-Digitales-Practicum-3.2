package com.aesuriagasalazar.competenciadigitalespracticum3_2.domain

import androidx.annotation.DrawableRes

data class Syllabus(
    val id: TopicSyllabusId,
    @DrawableRes val icon: Int,
    val title: String,
    val isComplete: Boolean
)