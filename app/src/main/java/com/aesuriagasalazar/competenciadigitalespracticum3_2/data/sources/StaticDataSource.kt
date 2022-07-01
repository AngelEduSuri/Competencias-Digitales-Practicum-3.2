package com.aesuriagasalazar.competenciadigitalespracticum3_2.data.sources

import com.aesuriagasalazar.competenciadigitalespracticum3_2.R
import com.aesuriagasalazar.competenciadigitalespracticum3_2.domain.Message
import com.aesuriagasalazar.competenciadigitalespracticum3_2.domain.Syllabus
import javax.inject.Inject

class StaticDataSource @Inject constructor() {

    val initialMessage = Message(
        lottieAnim = R.raw.initial_message_lottie,
        title = "Compartir contenido por medio de herramientas digitales sencillos",
        body = "Esta aplicación fue desarrollada con el objetivo de enseñarle al " +
                "usuario los pasos básicos para compartir contenido por medio de " +
                "herramientas digitales, tales como: redes sociales, correo electrónico, mensajería " +
                "instantánea, etc.",
    )

    val syllabusList = listOf(
        Syllabus(icon = R.drawable.file_icon, title = "¿Qué son los archivos?", isComplete = false),
        Syllabus(icon = R.drawable.file_type_icon, title = "Tipos de archivos", isComplete = false),
        Syllabus(icon = R.drawable.file_tools_icon, title = "Herramientas digitales", isComplete = false),
        Syllabus(icon = R.drawable.file_shrare_icon, title = "Compartir archivos", isComplete = false)
    )
}