package com.aesuriagasalazar.competenciadigitalespracticum3_2.ui.screens.lesson

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun LessonScreen(id: Int) {

    Column {
        Text(text = "Pantalla de los temas")
        Text(text = "Buscar la leccion con el id: $id")
    }

}