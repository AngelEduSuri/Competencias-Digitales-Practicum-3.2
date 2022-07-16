package com.aesuriagasalazar.competenciadigitalespracticum3_2.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.aesuriagasalazar.competenciadigitalespracticum3_2.domain.TopicSyllabusId
import com.aesuriagasalazar.competenciadigitalespracticum3_2.ui.screens.lesson.LessonScreen
import com.aesuriagasalazar.competenciadigitalespracticum3_2.ui.screens.lessonfinished.LessonFinishedScreen
import com.aesuriagasalazar.competenciadigitalespracticum3_2.ui.screens.lessonintroduction.IntroductionScreen
import com.aesuriagasalazar.competenciadigitalespracticum3_2.ui.screens.menu.MenuScreen
import com.aesuriagasalazar.competenciadigitalespracticum3_2.ui.screens.message.InitialMessageScreen
import com.aesuriagasalazar.competenciadigitalespracticum3_2.ui.screens.score.ScoreScreen
import com.aesuriagasalazar.competenciadigitalespracticum3_2.ui.screens.splash.SplashScreen
import com.aesuriagasalazar.competenciadigitalespracticum3_2.ui.screens.syllabus.SyllabusScreen
import com.aesuriagasalazar.competenciadigitalespracticum3_2.ui.screens.test.TestSyllabusScreen
import com.aesuriagasalazar.competenciadigitalespracticum3_2.ui.screens.testfinished.TestFinishedScreen
import com.aesuriagasalazar.competenciadigitalespracticum3_2.ui.screens.testintroduction.TestIntroductionScreen

@Composable
fun NavigationApp() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = RoutesApp.SplashScreen.route
    ) {
        composable(route = RoutesApp.SplashScreen.route) {
            SplashScreen(onNextScreen = { route, popUp ->
                navController.navigate(route = route) {
                    popUpTo(route = popUp) {
                        inclusive = true
                    }
                }
            })
        }

        composable(route = RoutesApp.InitialMessage.route) {
            InitialMessageScreen(
                onNextButtonClick = { route, popUp ->
                    navController.navigate(route = route) {
                        popUpTo(route = popUp) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable(route = RoutesApp.MenuApp.route) {
            MenuScreen(
                onNextScreen = { route ->
                    navController.navigate(route = route)
                }
            )
        }

        composable(
            route = RoutesApp.Syllabus.route,
        ) {
            SyllabusScreen(
                onNextScreen = { idTopic ->
                    navController.navigate(route = RoutesApp.LessonIntroduction.createRoute(idTopic))
                },
                onBackPressed = { navController.popBackStack() }
            )
        }

        composable(
            route = RoutesApp.LessonIntroduction.route,
            arguments = listOf(navArgument(name = RoutesApp.LessonIntroduction.arg) {
                type = NavType.EnumType(TopicSyllabusId::class.java)
            })
        ) {
            IntroductionScreen(onNavigate = { route0rNull ->
                route0rNull?.let { route ->
                    navController.navigate(
                        route = route
                    ) {
                        popUpTo(route = RoutesApp.Syllabus.route)
                    }
                } ?: navController.popBackStack()
            }, onBackPressed = { navController.popBackStack() })
        }

        composable(
            route = RoutesApp.Lesson.route,
            arguments = listOf(navArgument(name = RoutesApp.Lesson.arg) {
                type = NavType.EnumType(TopicSyllabusId::class.java)
            }, navArgument(name = RoutesApp.Lesson.title) {
                type = NavType.StringType
            })
        ) {
            LessonScreen(
                onBackPressed = { navController.popBackStack() },
                onLessonFinished = { route, popUp ->
                    navController.navigate(route = route) {
                        popUpTo(route = popUp)
                    }
                }
            )
        }

        composable(
            route = RoutesApp.LessonFinished.route,
            arguments = listOf(navArgument(name = RoutesApp.LessonFinished.arg) {
                type = NavType.EnumType(TopicSyllabusId::class.java)
            })
        ) {
            LessonFinishedScreen(onBackScreen = { navController.popBackStack() })
        }

        composable(route = RoutesApp.TestIntroduction.route) {
            TestIntroductionScreen(
                onBackScreen = { navController.popBackStack() },
                onStartTest = { route, popUp ->
                    navController.navigate(route = route) {
                        popUpTo(route = popUp)
                    }
                }
            )
        }

        composable(route = RoutesApp.Test.route) {
            TestSyllabusScreen(
                onBackScreen = { navController.popBackStack() },
                onTestFinished = { route, popUp ->
                    navController.navigate(route = route) {
                        popUpTo(popUp)
                    }
                }
            )
        }

        composable(
            route = RoutesApp.TestFinished.route,
            arguments = listOf(navArgument(name = RoutesApp.TestFinished.arg) {
                type = NavType.IntType
            })
        ) {
            TestFinishedScreen(onBackScreen = {
                navController.popBackStack()
            }, onTestScreen = {
                navController.navigate(route = it)
            })
        }

        composable(route = RoutesApp.Score.route) {
            ScoreScreen(onBackScreen = { navController.popBackStack() })
        }
    }
}

