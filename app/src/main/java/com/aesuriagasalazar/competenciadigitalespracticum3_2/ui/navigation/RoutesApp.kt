package com.aesuriagasalazar.competenciadigitalespracticum3_2.ui.navigation

import com.aesuriagasalazar.competenciadigitalespracticum3_2.domain.TopicSyllabusId

sealed class RoutesApp(val route: String) {

    object SplashScreen : RoutesApp("splash")
    object InitialMessage : RoutesApp("initial")
    object MenuApp : RoutesApp("menu")
    object Syllabus : RoutesApp("syllabus")
    object Introduction : RoutesApp("introduction/{topicId}") {
        const val arg = "topicId"
        fun createRoute(topicId: TopicSyllabusId) = "introduction/$topicId"
    }

    object Lesson : RoutesApp("lesson/{lessonId}/{title}") {
        const val arg = "lessonId"
        const val title = "title"
        fun createRoute(lessonId: TopicSyllabusId, titleArg: String) = "lesson/$lessonId/$titleArg"
    }

    object LessonFinished : RoutesApp("finished")
    object Test : RoutesApp("test")
    object Result : RoutesApp("result")
}
