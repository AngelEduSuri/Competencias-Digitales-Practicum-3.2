package com.aesuriagasalazar.competenciadigitalespracticum3_2.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.aesuriagasalazar.competenciadigitalespracticum3_2.domain.TopicSyllabusId
import com.aesuriagasalazar.competenciadigitalespracticum3_2.ui.screens.introduction.IntroductionScreen
import com.aesuriagasalazar.competenciadigitalespracticum3_2.ui.screens.lesson.LessonScreen
import com.aesuriagasalazar.competenciadigitalespracticum3_2.ui.screens.lessonfinished.LessonFinishedScreen
import com.aesuriagasalazar.competenciadigitalespracticum3_2.ui.screens.menu.MenuScreen
import com.aesuriagasalazar.competenciadigitalespracticum3_2.ui.screens.message.InitialMessageScreen
import com.aesuriagasalazar.competenciadigitalespracticum3_2.ui.screens.result.ResultScreen
import com.aesuriagasalazar.competenciadigitalespracticum3_2.ui.screens.splash.SplashScreen
import com.aesuriagasalazar.competenciadigitalespracticum3_2.ui.screens.syllabus.SyllabusScreen
import com.aesuriagasalazar.competenciadigitalespracticum3_2.ui.screens.test.TestScreen

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
                    navController.navigate(route = RoutesApp.Introduction.createRoute(idTopic))
                },
                onBackPressed = { navController.popBackStack() }
            )
        }

        composable(
            route = RoutesApp.Introduction.route,
            arguments = listOf(navArgument(name = RoutesApp.Introduction.arg) {
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

        composable(route = RoutesApp.LessonFinished.route) {
            LessonFinishedScreen()
        }

        composable(route = RoutesApp.Test.route) {
            TestScreen()
        }

        composable(route = RoutesApp.Result.route) {
            ResultScreen()
        }

    }
}

