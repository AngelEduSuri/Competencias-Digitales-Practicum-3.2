package com.aesuriagasalazar.competenciadigitalespracticum3_2.common

import android.util.Log

object PrintLog {

    fun print(location: String, value: Any) {
        Log.i("leer", "$location: $value")
    }
}