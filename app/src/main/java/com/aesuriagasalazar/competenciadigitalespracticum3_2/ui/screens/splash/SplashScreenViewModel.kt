package com.aesuriagasalazar.competenciadigitalespracticum3_2.ui.screens.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aesuriagasalazar.competenciadigitalespracticum3_2.data.repositories.LocalDataRepository
import com.aesuriagasalazar.competenciadigitalespracticum3_2.ui.navigation.RoutesApp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor(private val repository: LocalDataRepository): ViewModel() {

    fun onAppStart(onNextScreenPopUp: (String, String) -> Unit) {
        viewModelScope.launch {
            if (repository.getShowInitialMessage()) {
                onNextScreenPopUp(RoutesApp.MenuApp.route, RoutesApp.SplashScreen.route)
            } else {
                onNextScreenPopUp(RoutesApp.InitialMessage.route, RoutesApp.SplashScreen.route)
            }
        }
    }
}
