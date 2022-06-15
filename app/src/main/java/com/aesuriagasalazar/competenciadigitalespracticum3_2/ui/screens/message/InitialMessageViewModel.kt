package com.aesuriagasalazar.competenciadigitalespracticum3_2.ui.screens.message

import androidx.lifecycle.ViewModel
import com.aesuriagasalazar.competenciadigitalespracticum3_2.data.repositories.MessageRepository
import com.aesuriagasalazar.competenciadigitalespracticum3_2.model.Message
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class InitialMessageViewModel @Inject constructor(private val messageRepository: MessageRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(
        InitialMessageUiState(
            message = messageRepository.getInitialMessage(),
            false
        )
    )

    val uiState: StateFlow<InitialMessageUiState> = _uiState.asStateFlow()

    fun onCheckChanged(value: Boolean) {
        _uiState.update {
            it.copy(isChecked = value)
        }
    }

    fun onSaveNotShowInitialMessage() {
        messageRepository.saveNotShowInitialMessage(uiState.value.isChecked)
    }
}

data class InitialMessageUiState(
    val message: Message,
    val isChecked: Boolean
)