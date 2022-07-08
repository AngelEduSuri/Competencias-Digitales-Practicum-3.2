package com.aesuriagasalazar.competenciadigitalespracticum3_2.ui.screens.lesson

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aesuriagasalazar.competenciadigitalespracticum3_2.R
import com.aesuriagasalazar.competenciadigitalespracticum3_2.data.sources.StaticDataSource
import com.aesuriagasalazar.competenciadigitalespracticum3_2.domain.Lesson
import com.aesuriagasalazar.competenciadigitalespracticum3_2.domain.TopicSyllabusId
import com.aesuriagasalazar.competenciadigitalespracticum3_2.ui.navigation.RoutesApp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LessonViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val data: StaticDataSource
) : ViewModel() {

    private val _uiState = MutableStateFlow(LessonUiState())
    val uiState: StateFlow<LessonUiState> = _uiState.asStateFlow()

    init {
        getLessonsForId()
    }

    private fun getLessonsForId() = viewModelScope.launch {
        val listLesson = async(Dispatchers.IO) {
            val arg = savedStateHandle.get<TopicSyllabusId>(RoutesApp.Lesson.arg)
            requireNotNull(arg)
            data.getListLessonForId(arg)
        }
        val titleBar = async(Dispatchers.IO) {
            val arg = savedStateHandle.get<String>(RoutesApp.Lesson.title)
            requireNotNull(arg)
        }
        _uiState.update {
            it.copy(
                currentLesson = listLesson.await()[0],
                listLesson = listLesson.await(),
                titleBar = titleBar.await()
            )
        }
    }

    fun onNextLesson(onFinishLesson: (String, String) -> Unit) {
        if (uiState.value.lessonIndex < uiState.value.listLesson.size - 1) {
            _uiState.update {
                it.copy(lessonIndex = it.lessonIndex + 1)
            }
            updateLessonState()
        } else {
            onFinishLesson(
                RoutesApp.LessonFinished.createRoute(uiState.value.currentLesson.id),
                RoutesApp.Syllabus.route
            )
        }
    }

    fun onBackLesson() {
        if (uiState.value.lessonIndex != 0) {
            _uiState.update {
                it.copy(lessonIndex = it.lessonIndex - 1)
            }
            updateLessonState()
        }
    }

    private fun updateLessonState() {
        _uiState.update {
            it.copy(currentLesson = it.listLesson[it.lessonIndex])
        }
    }
}

data class LessonUiState(
    val titleBar: String = "",
    val lessonIndex: Int = 0,
    val currentLesson: Lesson = Lesson(
        TopicSyllabusId.NONE,
        R.drawable.icon_error,
        "",
        ""
    ),
    val listLesson: List<Lesson> = emptyList()
)
