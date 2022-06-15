package com.aesuriagasalazar.competenciadigitalespracticum3_2.model

import androidx.annotation.DrawableRes

data class Lesson(
    val message: String,
    @DrawableRes val image: Int,
)
