package com.aesuriagasalazar.competenciadigitalespracticum3_2.data.datasources

import com.aesuriagasalazar.competenciadigitalespracticum3_2.R
import com.aesuriagasalazar.competenciadigitalespracticum3_2.model.Message
import com.aesuriagasalazar.competenciadigitalespracticum3_2.model.UserActions
import javax.inject.Inject

class LocalDataSource @Inject constructor() {

    val initialMessage = Message(
        lottieAnim = R.raw.initial_message_lottie,
        title = "Compartir contenido por medio de herramientas digitales sencillos",
        body = "Esta aplicación fue desarrollada con el objetivo de enseñarle al " +
                "usuario los pasos básicos para compartir contenido por medio de " +
                "herramientas digitales, tales como: redes sociales, correo electrónico, mensajería " +
                "instantánea, etc.",
    )

    val menuUser = listOf(
        UserActions(title = "Instruime", R.drawable.learn, false),
        UserActions(title = "Evaluarme", R.drawable.test, false)
    )
}