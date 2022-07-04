package com.aesuriagasalazar.competenciadigitalespracticum3_2.data.sources

import com.aesuriagasalazar.competenciadigitalespracticum3_2.R
import com.aesuriagasalazar.competenciadigitalespracticum3_2.domain.MessageIntroduction
import com.aesuriagasalazar.competenciadigitalespracticum3_2.domain.Syllabus
import javax.inject.Inject

class StaticDataSource @Inject constructor() {

    val syllabusList = listOf(
        Syllabus(
            id = 1,
            icon = R.drawable.file_icon,
            title = "¿Qué son los archivos?",
            isComplete = false
        ),
        Syllabus(
            id = 2,
            icon = R.drawable.file_type_icon,
            title = "Tipos de archivos",
            isComplete = false
        ),
        Syllabus(
            id = 3,
            icon = R.drawable.file_tools_icon,
            title = "Herramientas digitales",
            isComplete = false
        ),
        Syllabus(
            id = 4,
            icon = R.drawable.file_shrare_icon,
            title = "Compartir archivos",
            isComplete = false
        )
    )

    private val topicList = listOf(
        MessageIntroduction(
            id = 1,
            title = "Tema - Archivos",
            lottieAnim = R.raw.introduction_message_animation,
            body = "En este tema aprenderás que son los archivos, su importancia y como compartirlos por medio de herramientas digitales como emails o redes sociales."
        ),
        MessageIntroduction(
            id = 2,
            title = "Tema - Tipo de archivos",
            lottieAnim = R.raw.introduction_message_animation,
            body = "En esta sección, conocerás los diferentes tipos de archivos que existen."
        ),
        MessageIntroduction(
            id = 3,
            title = "Tema - Herramientas",
            lottieAnim = R.raw.introduction_message_animation,
            body = "Ahora que conoce los tipos de archivos que existen, en esta sección revisaremos las diferentes herramientas digitales, por donde transitan todos estos archivos diariamente."
        ),
        MessageIntroduction(
            id = 4,
            title = "Tema - Compartir",
            lottieAnim = R.raw.introduction_message_animation,
            body = "Ya que conocemos las herramientas que nos permiten compartir todos estos archivos que hemos visto, vamos a ver como podemos usarlas."
        )
    )

    fun getIntroductionForId(topicId: Int) = topicList.find { it.id == topicId }

}