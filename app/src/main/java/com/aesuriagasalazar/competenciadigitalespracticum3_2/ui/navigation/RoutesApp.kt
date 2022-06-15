package com.aesuriagasalazar.competenciadigitalespracticum3_2.ui.navigation

sealed class RoutesApp(val route: String) {
    object InitialMessageApp: RoutesApp("initial")
    object MenuApp: RoutesApp("menu")
}
