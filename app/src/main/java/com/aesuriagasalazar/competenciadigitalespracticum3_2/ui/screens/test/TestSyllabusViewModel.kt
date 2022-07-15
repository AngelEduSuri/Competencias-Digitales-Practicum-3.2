package com.aesuriagasalazar.competenciadigitalespracticum3_2.ui.screens.test

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aesuriagasalazar.competenciadigitalespracticum3_2.data.sources.StaticDataSource
import com.aesuriagasalazar.competenciadigitalespracticum3_2.domain.TestSyllabus
import com.aesuriagasalazar.competenciadigitalespracticum3_2.ui.navigation.RoutesApp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TestSyllabusViewModel @Inject constructor(private val staticDataSource: StaticDataSource) :
    ViewModel() {

    private val _uiState = MutableStateFlow(TestSyllabusUiState())
    val uiState: StateFlow<TestSyllabusUiState> = _uiState.asStateFlow()

    init {
        getAllSyllabusTest()
    }

    private fun getAllSyllabusTest() = viewModelScope.launch {
        _uiState.update {
            it.copy(
                listOfTest = staticDataSource.getAllListQuestions().shuffled()
            )
        }
        currentTest()
    }

    private fun currentTest() {
        val currentTest = uiState.value.listOfTest[uiState.value.testIndex]
        val list =
            listOf(currentTest.questionOne, currentTest.questionTwo, currentTest.answer).shuffled()
        _uiState.update {
            it.copy(
                currentTest = list,
                questionTitle = currentTest.title,
                totalQuestionsCount = it.listOfTest.size
            )
        }
    }

    fun onAnswerSelected(answer: String) {
        _uiState.update {
            it.copy(answerSelected = answer)
        }
    }

    fun onCheckAnswer(onTestFinished: (String, String) -> Unit) {
        val state = _uiState.value
        if (state.answerSelected.isNotEmpty()) {
            _uiState.update { it.copy(enabledResponseButton = false) }
            if (uiState.value.listOfTest[uiState.value.testIndex].answer == uiState.value.answerSelected) {
                correctAnswer()
            } else {
                incorrectAnswer()
            }
            nextQuestion(onTestFinished = onTestFinished)
        } else {
            showMessageWhenAnswerIsEmpty()
        }
    }

    private fun showMessageWhenAnswerIsEmpty() = viewModelScope.launch {
        _uiState.update { it.copy(isAnyAnswerSelected = true) }
        delay(1500)
        _uiState.update { it.copy(isAnyAnswerSelected = false) }
    }

    private fun correctAnswer() {
        _uiState.update {
            it.copy(isAnswerCorrect = true, points = it.points + 1)
        }
    }

    private fun incorrectAnswer() {
        _uiState.update {
            it.copy(isAnswerCorrect = false)
        }
    }

    private fun nextQuestion(onTestFinished: (String, String) -> Unit) = viewModelScope.launch {
        if (uiState.value.testIndex < uiState.value.listOfTest.size - 1) {
            delay(1500)
            _uiState.update {
                it.copy(
                    isAnswerCorrect = null,
                    testIndex = it.testIndex + 1,
                    answerSelected = ""
                )
            }
            currentTest()
            _uiState.update { it.copy(enabledResponseButton = true) }
        } else {
            delay(1500)
            onTestFinished(
                RoutesApp.TestFinished.createRoute(uiState.value.points),
                RoutesApp.MenuApp.route
            )
        }
    }
}

data class TestSyllabusUiState(
    val listOfTest: List<TestSyllabus> = emptyList(),
    val currentTest: List<String> = emptyList(),
    val questionTitle: String = "",
    val testIndex: Int = 0,
    val answerSelected: String = "",
    val points: Int = 0,
    val isAnswerCorrect: Boolean? = null,
    val isAnyAnswerSelected: Boolean = false,
    val totalQuestionsCount: Int = 0,
    val enabledResponseButton: Boolean = true,
)