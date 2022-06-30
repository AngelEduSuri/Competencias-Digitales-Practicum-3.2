package com.aesuriagasalazar.competenciadigitalespracticum3_2.ui.screens.message

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aesuriagasalazar.competenciadigitalespracticum3_2.data.sources.StaticDataSource
import com.aesuriagasalazar.competenciadigitalespracticum3_2.data.repositories.LocalDataRepository
import com.aesuriagasalazar.competenciadigitalespracticum3_2.model.Message
import com.aesuriagasalazar.competenciadigitalespracticum3_2.ui.navigation.RoutesApp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InitialMessageViewModel @Inject constructor(
    private val localDataRepository: LocalDataRepository,
    staticDataSource: StaticDataSource
) : ViewModel() {

    private val _uiState = MutableStateFlow(
        InitialMessageUiState(
            message = staticDataSource.initialMessage,
            isChecked = false
        )
    )

    val uiState: StateFlow<InitialMessageUiState> = _uiState.asStateFlow()

    fun onCheckChanged(value: Boolean) {
        _uiState.update { it.copy(isChecked = value) }
    }

    fun onSaveCheckAndNavigate(onMenuScreen: (String, String) -> Unit) {
        viewModelScope.launch {
            localDataRepository.saveShowInitialMessage(uiState.value.isChecked)
            onMenuScreen(RoutesApp.MenuApp.route, RoutesApp.InitialMessage.route)
        }
    }
}

data class InitialMessageUiState(
    val message: Message,
    val isChecked: Boolean = false
)