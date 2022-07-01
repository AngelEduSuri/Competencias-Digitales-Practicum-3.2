package com.aesuriagasalazar.competenciadigitalespracticum3_2.domain

import androidx.annotation.DrawableRes

data class Lesson(
    val message: String,
    @DrawableRes val image: Int,
)
