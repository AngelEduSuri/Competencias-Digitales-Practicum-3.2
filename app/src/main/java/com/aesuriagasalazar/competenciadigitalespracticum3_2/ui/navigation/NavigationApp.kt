package com.aesuriagasalazar.competenciadigitalespracticum3_2.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aesuriagasalazar.competenciadigitalespracticum3_2.data.datasources.SharedPrefDataSource
import com.aesuriagasalazar.competenciadigitalespracticum3_2.ui.screens.menu.MenuScreen
import com.aesuriagasalazar.competenciadigitalespracticum3_2.ui.screens.menu.MenuViewModel
import com.aesuriagasalazar.competenciadigitalespracticum3_2.ui.screens.message.InitialMessageScreen
import com.aesuriagasalazar.competenciadigitalespracticum3_2.ui.screens.message.InitialMessageViewModel

@Composable
fun NavigationApp() {

    val navController = rememberNavController()
    val context = LocalContext.current
    val sharedPrefDataSource = SharedPrefDataSource(context)

    Scaffold {
        NavHost(
            navController = navController,
            startDestination = if (sharedPrefDataSource.getBooleanPref()) RoutesApp.MenuApp.route else RoutesApp.InitialMessageApp.route,
            modifier = Modifier.padding(it)
        ) {
            composable(route = RoutesApp.InitialMessageApp.route) {
                val viewModel = hiltViewModel<InitialMessageViewModel>()
                InitialMessageScreen(viewModel = viewModel) {
                    navController.navigate(route = RoutesApp.MenuApp.route) {
                        popUpTo(route = RoutesApp.InitialMessageApp.route) {
                            inclusive = true
                        }
                    }
                }
            }

            composable(route = RoutesApp.MenuApp.route) {
                val viewModel = hiltViewModel<MenuViewModel>()
                MenuScreen(viewModel = viewModel)
            }
        }
    }
}