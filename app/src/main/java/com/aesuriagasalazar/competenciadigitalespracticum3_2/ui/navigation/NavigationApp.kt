package com.aesuriagasalazar.competenciadigitalespracticum3_2.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aesuriagasalazar.competenciadigitalespracticum3_2.ui.screens.introduction.IntroductionScreen
import com.aesuriagasalazar.competenciadigitalespracticum3_2.ui.screens.menu.MenuScreen
import com.aesuriagasalazar.competenciadigitalespracticum3_2.ui.screens.message.InitialMessageScreen
import com.aesuriagasalazar.competenciadigitalespracticum3_2.ui.screens.result.ResultScreen
import com.aesuriagasalazar.competenciadigitalespracticum3_2.ui.screens.splash.SplashScreen
import com.aesuriagasalazar.competenciadigitalespracticum3_2.ui.screens.syllabus.SyllabusScreen
import com.aesuriagasalazar.competenciadigitalespracticum3_2.ui.screens.test.TestScreen

@Composable
fun NavigationApp() {

    val navController = rememberNavController()

    Scaffold {
        NavHost(
            navController = navController,
            startDestination = RoutesApp.SplashScreen.route,
            modifier = Modifier.padding(it)
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

            composable(route = RoutesApp.Syllabus.route) {
                SyllabusScreen(onNextScreen = {
                    navController.navigate(route = RoutesApp.Introduction.route)
                })
            }

            composable(route = RoutesApp.Introduction.route) {
                IntroductionScreen()
            }

            composable(route = RoutesApp.Test.route) {
                TestScreen()
            }

            composable(route = RoutesApp.Result.route) {
                ResultScreen()
            }

        }
    }
}

