package com.aesuriagasalazar.competenciadigitalespracticum3_2.ui.screens.menu

import androidx.lifecycle.ViewModel
import com.aesuriagasalazar.competenciadigitalespracticum3_2.data.datasources.LocalDataSource
import com.aesuriagasalazar.competenciadigitalespracticum3_2.model.UserActions
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(private val localDataSource: LocalDataSource): ViewModel() {
    private val _uiState = MutableStateFlow(MenuUiState(buttons = localDataSource.menuUser))

    val uiState: StateFlow<MenuUiState> = _uiState.asStateFlow()
}

data class MenuUiState(
    val name: String = "No User",
    val buttons: List<UserActions> = emptyList()
)

