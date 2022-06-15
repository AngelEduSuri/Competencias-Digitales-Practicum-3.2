@file:OptIn(ExperimentalMaterialApi::class)

package com.aesuriagasalazar.competenciadigitalespracticum3_2.ui.screens.menu

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.aesuriagasalazar.competenciadigitalespracticum3_2.R
import com.aesuriagasalazar.competenciadigitalespracticum3_2.model.UserActions

@Composable
fun MenuScreen(viewModel: MenuViewModel) {

    val userMenu = viewModel.uiState.collectAsState().value

    MenuBody(userMenu)
}

@Composable
fun MenuBody(userMenu: MenuUiState) {
    Column(modifier = Modifier.fillMaxSize()) {
        UserData(
            modifier = Modifier.weight(0.2f),
            name = userMenu.name
        )
        MenuActions(
            modifier = Modifier.weight(0.8f),
            buttons = userMenu.buttons
        )
    }
}

@Composable
fun UserData(modifier: Modifier = Modifier, name: String) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(
            text = stringResource(R.string.welcome),
            style = MaterialTheme.typography.h4
        )
        Text(
            text = name,
            style = MaterialTheme.typography.h5
        )
    }
}

@Composable
fun MenuActions(modifier: Modifier = Modifier, buttons: List<UserActions>) {
    Surface(
        modifier = modifier,
        elevation = 8.dp,
        color = MaterialTheme.colors.secondary,
        shape = MaterialTheme.shapes.small.copy(
            topStart = CornerSize(size = 8.dp),
            topEnd = CornerSize(size = 8.dp)
        )
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(space = 8.dp),
            contentPadding = PaddingValues(
                horizontal = 8.dp,
                vertical = 16.dp
            ),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            items(buttons) {
                ButtonMenu(userActions = it, onCheckedChanged = {})
            }

            item {
                Button(onClick = { /*TODO*/ }) {
                    Text(text = stringResource(R.string.my_result))
                }
            }
        }
    }
}

@Composable
fun ButtonMenu(
    userActions: UserActions,
    onCheckedChanged: (Boolean) -> Unit
) {
    Card(
        onClick = { /*TODO*/ },
        elevation = 8.dp,
        shape = MaterialTheme.shapes.small
    ) {
        Box(modifier = Modifier
            .height(180.dp)
            .fillMaxWidth()) {
            Image(
                painter = painterResource(id = userActions.image),
                contentDescription = userActions.title,
                contentScale = ContentScale.Crop
            )
            Text(
                modifier = Modifier.align(alignment = Alignment.BottomCenter),
                text = userActions.title,
                style = MaterialTheme.typography.h4.copy(color = Color.White),
                fontWeight = FontWeight.Bold
            )
            Checkbox(checked = userActions.isComplete, onCheckedChange = onCheckedChanged)
        }
    }
}