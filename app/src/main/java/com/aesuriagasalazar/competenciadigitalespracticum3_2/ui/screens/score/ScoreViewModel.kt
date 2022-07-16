package com.aesuriagasalazar.competenciadigitalespracticum3_2.ui.screens.score

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aesuriagasalazar.competenciadigitalespracticum3_2.data.repositories.ResultDataRepository
import com.aesuriagasalazar.competenciadigitalespracticum3_2.domain.TestScore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScoreViewModel @Inject constructor(resultDataRepository: ResultDataRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(ScoreUiState())
    val uiState: StateFlow<ScoreUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            _uiState.update {
                it.copy(score = resultDataRepository.getTestResult())
            }
            updateScoreList()
        }
    }

    fun onScoreOrder() {
        _uiState.update {
            it.copy(orderBy = !it.orderBy)
        }
        updateScoreList()
    }

    private fun updateScoreList() {
        _uiState.update { scoreUiState ->
            return@update if (scoreUiState.orderBy) {
                scoreUiState.copy(score = scoreUiState.score.sortedByDescending { it.score })
            } else {
                scoreUiState.copy(score = scoreUiState.score.sortedBy { it.score })
            }
        }
    }

}

data class ScoreUiState(
    val score: List<TestScore> = emptyList(),
    val orderBy: Boolean = true
)