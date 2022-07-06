package com.aesuriagasalazar.competenciadigitalespracticum3_2.ui.screens.introduction

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.aesuriagasalazar.competenciadigitalespracticum3_2.R
import com.aesuriagasalazar.competenciadigitalespracticum3_2.data.sources.StaticDataSource
import com.aesuriagasalazar.competenciadigitalespracticum3_2.domain.MessageIntroduction
import com.aesuriagasalazar.competenciadigitalespracticum3_2.domain.TopicSyllabusId
import com.aesuriagasalazar.competenciadigitalespracticum3_2.ui.navigation.RoutesApp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class IntroductionViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val data: StaticDataSource
) : ViewModel() {

    private val _uiState = MutableStateFlow(IntroductionUiState())
    val uiState: StateFlow<IntroductionUiState> = _uiState.asStateFlow()

    init {
        getTopicForId()
    }

    private fun getTopicForId() {
        val id = savedStateHandle.get<TopicSyllabusId>(RoutesApp.Introduction.arg)
        requireNotNull(id)
        data.getIntroductionForId(topicId = id)?.let { topic ->
            _uiState.update {
                it.copy(topic = topic, isContentEmpty = false)
            }
        }
    }

    fun onNavigateScreen(navigate: (String?) -> Unit) {
        if (uiState.value.isContentEmpty) {
            navigate(null)
        } else {
            navigate(
                RoutesApp.Lesson.createRoute(
                    lessonId = uiState.value.topic.id,
                    titleArg = uiState.value.topic.title
                )
            )
        }
    }
}

data class IntroductionUiState(
    val isContentEmpty: Boolean = true,
    val topic: MessageIntroduction = MessageIntroduction(
        id = TopicSyllabusId.NONE,
        title = "No encontrado",
        lottieAnim = R.raw.content_not_found_animation,
        body = "Este contenido no está disponible por ahora."
    )
)