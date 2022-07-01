package com.aesuriagasalazar.competenciadigitalespracticum3_2.ui.navigation

sealed class RoutesApp(val route: String) {
    object SplashScreen : RoutesApp("splash")
    object InitialMessage : RoutesApp("initial")
    object MenuApp : RoutesApp("menu")
    object Syllabus : RoutesApp("syllabus")
    object Lesson : RoutesApp("lesson")
    object Introduction : RoutesApp("introduction")
    object Test : RoutesApp("test")
    object Result : RoutesApp("result")
}
