package com.aesuriagasalazar.competenciadigitalespracticum3_2.domain

sealed class UserAuthResponse<out T> {
    object Loading : UserAuthResponse<Nothing>()
    data class Success<out T>(val result: T?) : UserAuthResponse<T>()
    data class Failure(val exception: Exception?) : UserAuthResponse<Nothing>()
}
