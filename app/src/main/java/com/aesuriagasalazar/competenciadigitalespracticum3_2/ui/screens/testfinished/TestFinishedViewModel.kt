package com.aesuriagasalazar.competenciadigitalespracticum3_2.ui.screens.testfinished

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aesuriagasalazar.competenciadigitalespracticum3_2.data.repositories.ResultDataRepository
import com.aesuriagasalazar.competenciadigitalespracticum3_2.data.sources.StaticDataSource
import com.aesuriagasalazar.competenciadigitalespracticum3_2.domain.TestResult
import com.aesuriagasalazar.competenciadigitalespracticum3_2.ui.navigation.RoutesApp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class TestFinishedViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val staticDataSource: StaticDataSource,
    private val resultDataRepository: ResultDataRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(TestFinishedUiState())
    val uiState: StateFlow<TestFinishedUiState> = _uiState.asStateFlow()

    init {
        getTestResultFromArguments()
    }

    private fun getTestResultFromArguments() = viewModelScope.launch {
        _uiState.update {
            it.copy(
                result = requireNotNull(savedStateHandle[RoutesApp.TestFinished.arg]),
                totalQuestions = staticDataSource.getAllListQuestions().size
            )
        }
        saveTestResult()
    }

    private suspend fun saveTestResult() {
        val formatDate = SimpleDateFormat(
            "MM-dd-yyyy HH:mm:ss",
            Locale.getDefault()
        ).format(Calendar.getInstance().time)

        resultDataRepository.saveTestResult(TestResult(uiState.value.result, formatDate))
        resultDataRepository.saveTestCompleted(true)
    }
}

data class TestFinishedUiState(
    val result: Int = 0,
    val totalQuestions: Int = 0,
)