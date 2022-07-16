package com.aesuriagasalazar.competenciadigitalespracticum3_2.ui.navigation

import com.aesuriagasalazar.competenciadigitalespracticum3_2.domain.TopicSyllabusId

sealed class RoutesApp(val route: String) {

    object SplashScreen : RoutesApp("splash")
    object InitialMessage : RoutesApp("initial")
    object MenuApp : RoutesApp("menu")
    object Syllabus : RoutesApp("syllabus")
    object LessonIntroduction : RoutesApp("test_introduction/{topicId}") {
        const val arg = "topicId"
        fun createRoute(topicId: TopicSyllabusId) = "test_introduction/$topicId"
    }

    object Lesson : RoutesApp("lesson/{lessonId}/{title}") {
        const val arg = "lessonId"
        const val title = "title"
        fun createRoute(lessonId: TopicSyllabusId, titleArg: String) = "lesson/$lessonId/$titleArg"
    }

    object LessonFinished : RoutesApp("finished/{lessonId}") {
        const val arg = "lessonId"
        fun createRoute(lessonId: TopicSyllabusId) = "finished/$lessonId"
    }

    object TestIntroduction : RoutesApp("test_introduction")
    object Test : RoutesApp("test")
    object TestFinished : RoutesApp("test_finished/{score}") {
        const val arg = "score"
        fun createRoute(testScore: Int) = "test_finished/$testScore"
    }

    object Score : RoutesApp("score")
}
