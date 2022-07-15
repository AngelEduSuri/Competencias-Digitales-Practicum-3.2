package com.aesuriagasalazar.competenciadigitalespracticum3_2.domain

sealed class UserResponse<out T> {
    object Loading : UserResponse<Nothing>()
    data class Success<out T>(val result: T?) : UserResponse<T>()
    data class Failure(val exception: Exception?) : UserResponse<Nothing>()
}
