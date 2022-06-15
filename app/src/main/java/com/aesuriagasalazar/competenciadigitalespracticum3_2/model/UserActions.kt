package com.aesuriagasalazar.competenciadigitalespracticum3_2.model

import androidx.annotation.DrawableRes

data class UserActions(
    val title: String,
    @DrawableRes val image: Int,
    val isComplete: Boolean
)