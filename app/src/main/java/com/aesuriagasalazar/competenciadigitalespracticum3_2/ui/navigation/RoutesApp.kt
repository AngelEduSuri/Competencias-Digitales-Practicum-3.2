package com.aesuriagasalazar.competenciadigitalespracticum3_2.ui.navigation

sealed class RoutesApp(val route: String) {

    object SplashScreen : RoutesApp("splash")
    object InitialMessage : RoutesApp("initial")
    object MenuApp : RoutesApp("menu")
    object Syllabus : RoutesApp("syllabus")
    object Introduction : RoutesApp("introduction/{topicId}") {
        const val arg = "topicId"
        fun createRoute(topicId: Int) = "introduction/$topicId"
    }
    object Lesson : RoutesApp("lesson/{lessonId}") {
        const val arg = "lessonId"
        fun createRout(lessonId: Int) = "lesson/$lessonId"
    }
    object Test : RoutesApp("test")
    object Result : RoutesApp("result")
}
