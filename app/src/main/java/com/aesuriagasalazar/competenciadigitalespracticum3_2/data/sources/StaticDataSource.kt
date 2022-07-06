package com.aesuriagasalazar.competenciadigitalespracticum3_2.data.sources

import com.aesuriagasalazar.competenciadigitalespracticum3_2.R
import com.aesuriagasalazar.competenciadigitalespracticum3_2.domain.Lesson
import com.aesuriagasalazar.competenciadigitalespracticum3_2.domain.MessageIntroduction
import com.aesuriagasalazar.competenciadigitalespracticum3_2.domain.Syllabus
import com.aesuriagasalazar.competenciadigitalespracticum3_2.domain.TopicSyllabusId
import javax.inject.Inject

class StaticDataSource @Inject constructor() {

    private val syllabusList = listOf(
        Syllabus(
            id = TopicSyllabusId.TOPIC_FILE,
            icon = R.drawable.file_icon,
            title = "¿Qué son los archivos?",
            isComplete = false
        ),
        Syllabus(
            id = TopicSyllabusId.TYPE_OF_FILE,
            icon = R.drawable.file_type_icon,
            title = "Tipos de archivos",
            isComplete = false
        ),
        Syllabus(
            id = TopicSyllabusId.DIGITAL_TOOLS,
            icon = R.drawable.file_tools_icon,
            title = "Herramientas digitales",
            isComplete = false
        ),
        Syllabus(
            id = TopicSyllabusId.SHARE_FILE,
            icon = R.drawable.file_shrare_icon,
            title = "Compartir archivos",
            isComplete = false
        )
    )

    fun getSyllabusList(): List<Syllabus> = syllabusList

    private val topicList = listOf(
        MessageIntroduction(
            id = TopicSyllabusId.TOPIC_FILE,
            title = "Tema - Archivos",
            lottieAnim = R.raw.introduction_message_animation,
            body = "En este tema aprenderás que son los archivos, su importancia y como compartirlos por medio de herramientas digitales como emails o redes sociales."
        ),
        MessageIntroduction(
            id = TopicSyllabusId.TYPE_OF_FILE,
            title = "Tema - Tipo de archivos",
            lottieAnim = R.raw.introduction_message_animation,
            body = "En esta sección, conocerás los diferentes tipos de archivos que existen."
        ),
        MessageIntroduction(
            id = TopicSyllabusId.DIGITAL_TOOLS,
            title = "Tema - Herramientas",
            lottieAnim = R.raw.introduction_message_animation,
            body = "Ahora que conoce los tipos de archivos que existen, en esta sección revisaremos las diferentes herramientas digitales, por donde transitan todos estos archivos diariamente."
        ),
        MessageIntroduction(
            id = TopicSyllabusId.SHARE_FILE,
            title = "Tema - Compartir",
            lottieAnim = R.raw.introduction_message_animation,
            body = "Ya que conocemos las herramientas que nos permiten compartir todos estos archivos que hemos visto, vamos a ver como podemos usarlas."
        )
    )

    fun getIntroductionForId(topicId: TopicSyllabusId) = topicList.find { it.id == topicId }

    private val lessonList = listOf(
        Lesson(
            id = TopicSyllabusId.TOPIC_FILE,
            image = R.drawable.file_lesson_1,
            title = "¿Qué son los archivos?",
            message = "Los archivos son un conjunto de información almacenada en su interior y que se puede identificar con un nombre. Por ejemplo: \"Fotos de mis vacaciones\", \"Mis músicas favoritas\", \"Documento de mi trabajo\", entre otras."
        ),
        Lesson(
            id = TopicSyllabusId.TOPIC_FILE,
            image = R.drawable.file_lesson_2,
            title = "¿Qué es esta información?",
            message = "Esta información almacenada permite a un dispositivo como computadora, celular, tabletas, entre otros, identificar qué tipo de archivo es y cómo puede abrirlo."
        ),
        Lesson(
            id = TopicSyllabusId.TOPIC_FILE,
            image = R.drawable.file_lesson_3,
            title = "¿Y cómo los abre?",
            message = "Cuando el dispositivo indentifica que tipo de archivo es, elige la aplicación que puede abrir dicho archivo para mostrarselo al usuario. Por ejemplo: si es una imagen, abre la galería. Si es un documento abre el procesador de texto. Y si es un audio o video, se abre el reproductor de música o video respectivamente."
        ),
        Lesson(
            id = TopicSyllabusId.TYPE_OF_FILE,
            image = R.drawable.type_file_lesson_1,
            title = "¿Qué tipo de archivos existen?",
            message = "La información almacenada dentro de los diferentes archivos que existen, permiten identificar de qué tipo son, tales como: imágenes, videos, audios, textos y otros más."
        ),
        Lesson(
            id = TopicSyllabusId.TYPE_OF_FILE,
            image = R.drawable.type_file_lesson_2,
            title = "Imágenes",
            message = "Son aquellos archivos que guardan una representación visual como una foto o un dibujo, estas imágenes generalmente se presentan en formato JPG, así existen también, imágenes con movimiento que son en formato GIF y son las que habitualmente se comparten por redes sociales."
        ),
        Lesson(
            id = TopicSyllabusId.TYPE_OF_FILE,
            image = R.drawable.type_file_lesson_3,
            title = "Audios",
            message = "Estos archivos contienen información acerca de todo lo relacionado con el sonido, como puede ser la música o la voz que generamos. Los tipos más usados por gran parte de las personas son el MP3 y WAV que se pueden encontrar en la música que escuchas todo el tiempo."
        ),
        Lesson(
            id = TopicSyllabusId.TYPE_OF_FILE,
            image = R.drawable.type_file_lesson_4,
            title = "Videos",
            message = "Los archivos de video son una mezcla de imágenes y audios en conjunto. Entre los tipos más comunes tenemos el MP4, AVI y FLV, estos los podemos encontrar en las películas o videos que encuentras por Youtube, Facebook o en alguna plataforma de distribución de contenido digital como Netflix, Disney+, entre otras."
        ),
        Lesson(
            id = TopicSyllabusId.TYPE_OF_FILE,
            image = R.drawable.type_file_lesson_5,
            title = "Textos",
            message = "Estos archivos de texto son aquellos que se presentan de forma escrita por medio de un dispositivo como computadoras, celulares o tabletas, donde el resultado de esta informacion agrupada se denomina documento digital como los que usualmente escribimos por Microsoft Word, también los archivos de texto se presentan en los mensajes que envías a través de las redes sociales o mensajería. Entre los tipos más utilizados están: TXT, DOCX y PDF."
        ),
        Lesson(
            id = TopicSyllabusId.DIGITAL_TOOLS,
            image = R.drawable.digital_tools_lesson_1,
            title = "¿Qué herramientas existen?",
            message = "Existen muchas herramientas donde estos archivos circulan diariamente como son: correos electronicos (emails), redes sociales, mensajería, etc. A continuación revisaremos algunas de ellas."
        ),
        Lesson(
            id = TopicSyllabusId.DIGITAL_TOOLS,
            image = R.drawable.digital_tools_lesson_2,
            title = "Correo Electrónico (Email)",
            message = "Los correos electrónicos o también llamados emails, permiten el intercambio de mensajes entre varias personas. Normalmente son usados para enviar texto, documentos y/o imágenes. Existen diferentes servicios de correo electrónico como: Hotmail, Gmail y Yahoo, seguramente has escuchado o usado alguno de ellos."
        ),
        Lesson(
            id = TopicSyllabusId.DIGITAL_TOOLS,
            image = R.drawable.digital_tools_lesson_3,
            title = "Redes Sociales",
            message = "Entre estas herramientas, tenemos a Facebook cuyo principal objetivo es producir y compartir contenido como imágenes o videos, otra herramienta con este mismo objetivo es Instagram. En cambio Twitter es otra red social que se enfoca en la comunicación por fragmentos de texto, aunque también es muy usada para compartir imágenes y videos cortos."
        ),
        Lesson(
            id = TopicSyllabusId.DIGITAL_TOOLS,
            image = R.drawable.digital_tools_lesson_4,
            title = "Mensajería",
            message = "En la mensajería tenemos algunas herramientas entre ellas WhatsApp, Telegram y Messenger, que son las mayormente extendidas, aunque su objetivo es la comunicación entre diferentes usuarios mediante texto, han evolucionado para compartir todo tipo de archivos como imágenes, audios, videos, etc."
        ),
        Lesson(
            id = TopicSyllabusId.SHARE_FILE,
            image = R.drawable.share_file_lesson_1,
            title = "¿Cómo podemos compartir?",
            message = "Con los tipos de archivos y herramientas estudiadas anteriormente, podemos usarlas en nuestro beneficio y en muchos ámbitos del día a día como se mostrará a continuación."
        ),
        Lesson(
            id = TopicSyllabusId.SHARE_FILE,
            image = R.drawable.share_file_lesson_2,
            title = "Compartir Texto",
            message = "Este es el medio más sencillo y es el más utilizado. Muchas veces sin darnos cuenta hacemos uso de todas las herramientas digitales, ya sea emails, redes sociales o mensajería, como puede ser cuando compartirmos un texto que escribimos en un mensaje a nuestros familiares y amigos, cuando enviamos un correo y/o respondemos un comentario en facebook, twitter, etc."
        ),
        Lesson(
            id = TopicSyllabusId.SHARE_FILE,
            image = R.drawable.share_file_lesson_3,
            title = "¿Y qué hay de los documentos?",
            message = "Si queremos compartir documentos, debemos conocer el ICONO (como se visualiza en la imagen superior con forma de clip) cuya palabra es ADJUNTAR y su significado es un archivo enviado junto con un mensaje, sucede muy a menudo cuando se envía un email junto con un archivo(imagen, video o documento)."
        ),
        Lesson(
            id = TopicSyllabusId.SHARE_FILE,
            image = R.drawable.share_file_lesson_4,
            title = "Compartir Imágenes",
            message = "Cuando queremos compartir imágenes debemos indentificar el nombre del archivo y su tipo, estos pueden ser: JPG, PNG o GIF, los archivos de imagen se pueden reconocer fácilmente por el ícono mostrado en la parte superior."
        ),
        Lesson(
            id = TopicSyllabusId.SHARE_FILE,
            image = R.drawable.share_file_lesson_5,
            title = "Compartir Videos",
            message = "Al igual que las imágenes, los videos se pueden compartir identificando su nombre y su tipo de archivo como MP4 o AVI siendo los más usados y que se pueden reconocer fácilmente por el clásico símbolo de REPRODUCIR que se puede ver en la imagen superior."
        ),
        Lesson(
            id = TopicSyllabusId.SHARE_FILE,
            image = R.drawable.file_type_icon,
            title = "¿Y cómo comparto estos archivos?",
            message = "Cuando ya tengas la imagen o video que quieras compartir mediante alguna de las herramientas conocidas, debes seguir los pasos explicados a continuación:"
        ),
        Lesson(
            id = TopicSyllabusId.SHARE_FILE,
            image = R.drawable.share_file_lesson_7,
            title = "Compartir en Facebook",
            message = "Ingresamos con nuestra cuenta de Facebook, ya sea desde un computador o celular, nos dirigimos a la sección de publicaciones, al crear una publicación podemos seleccionar la opción Foto/Video y elegimos nuestro archivo para compartir, lo seleccionamos y Facebook automáticamente subirá el archivo elegido."
        ),
        Lesson(
            id = TopicSyllabusId.SHARE_FILE,
            image = R.drawable.share_file_lesson_8,
            title = "Compartir en WhatsApp",
            message = "Por este medio, podemos compartir nuestros archivos con la opción ADJUNTAR, (recuerda el ícono del clip que vimos anteriormente), luego aparece un pequeño menú donde escogeremos GALERÍA o CÁMARA si queremos compartir fotos o videos. Así mismo, existen las opciones de AUDIO o DOCUMENTO si queremos compartir esos tipos de archivos."
        ),
        Lesson(
            id = TopicSyllabusId.SHARE_FILE,
            image = R.drawable.share_file_lesson_9,
            title = "Compartir voz por WhatsApp",
            message = "En WhatsApp para compartir un archivo de audio con nuestra voz, debemos seleccionar el ícono del MICRÓFONO (imagen arriba) que se encuentra a la derecha donde escribimos el mensaje y mantenemos presionado mientras hablamos, una vez finalizado podemos dejar de presionar el MICRÓFONO y nuestro mensaje de voz se enviará automáticamente."
        ),
        Lesson(
            id = TopicSyllabusId.SHARE_FILE,
            image = R.drawable.share_file_lesson_10,
            title = "Compartir en Telegram",
            message = "Telegram al ser un servicio de mensajería, su uso es el mismo descrito en las lecciones anteriores sobre WhatsApp, es decir si queremos compartir un archivo, debemos usar los mismos pasos vistos en el tema de WhatsApp, como: ADJUNTAR si queremos enviar un archivo de Imagen o Video o seleccionar el MICRÓFONO si el mensaje a enviar es de voz."
        )
    )

    fun getListLessonForId(idLesson: TopicSyllabusId) = lessonList.filter { it.id == idLesson }
}