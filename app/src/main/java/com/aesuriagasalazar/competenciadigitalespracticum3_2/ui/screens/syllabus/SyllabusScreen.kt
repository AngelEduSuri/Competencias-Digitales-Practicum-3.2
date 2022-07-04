package com.aesuriagasalazar.competenciadigitalespracticum3_2.ui.screens.syllabus

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.aesuriagasalazar.competenciadigitalespracticum3_2.R
import com.aesuriagasalazar.competenciadigitalespracticum3_2.domain.Syllabus
import com.aesuriagasalazar.competenciadigitalespracticum3_2.ui.theme.AppTheme

@Composable
fun SyllabusScreen(
    viewModel: SyllabusViewModel = hiltViewModel(),
    onNextScreen: (Int) -> Unit
) {

    val uiState = viewModel.syllabusUiState.collectAsState().value

    SyllabusScreenBody(uiState = uiState, onItemClick = {
        onNextScreen(it)
    })

}

@Composable
fun SyllabusScreenBody(
    uiState: SyllabusUiState,
    onItemClick: (Int) -> Unit
) {

    LazyColumn(
        contentPadding = PaddingValues(all = 8.dp),
        verticalArrangement = Arrangement.spacedBy(space = 8.dp)
    ) {
        items(uiState.listSyllabus) {
            SyllabusTheme(theme = it, onClick = { onItemClick(it.id) })
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SyllabusTheme(theme: Syllabus, onClick: () -> Unit) {

    Card(onClick = onClick, backgroundColor = MaterialTheme.colors.primary) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Image(
                modifier = Modifier.weight(weight = 0.2f),
                painter = painterResource(id = theme.icon),
                contentDescription = theme.title
            )
            Text(
                modifier = Modifier
                    .weight(weight = 0.7f)
                    .padding(all = 4.dp),
                text = theme.title,
                style = MaterialTheme.typography.h5,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )
            Checkbox(
                modifier = Modifier.weight(weight = 0.1f),
                checked = theme.isComplete,
                onCheckedChange = null
            )
        }
    }
}

@Preview
@Composable
fun SyllabusThemePreview() {

    AppTheme {
        SyllabusTheme(
            theme = Syllabus(
                1,
                R.drawable.file_shrare_icon,
                "Tipos de archivos",
                false
            ),
            onClick = {}
        )
    }
}