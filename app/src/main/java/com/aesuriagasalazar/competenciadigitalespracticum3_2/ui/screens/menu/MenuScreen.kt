package com.aesuriagasalazar.competenciadigitalespracticum3_2.ui.screens.menu

import android.app.Activity.RESULT_OK
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.aesuriagasalazar.competenciadigitalespracticum3_2.R
import com.aesuriagasalazar.competenciadigitalespracticum3_2.common.PrintLog
import com.aesuriagasalazar.competenciadigitalespracticum3_2.domain.UserAuthResponse
import com.aesuriagasalazar.competenciadigitalespracticum3_2.ui.components.ProgressIndicatorApp
import com.aesuriagasalazar.competenciadigitalespracticum3_2.ui.components.ButtonApp
import com.aesuriagasalazar.competenciadigitalespracticum3_2.ui.components.SurfaceApp
import com.google.android.gms.auth.api.identity.BeginSignInResult
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider

@Composable
fun MenuScreen(
    onNextScreen: (String) -> Unit,
    viewModel: MenuViewModel = hiltViewModel()
) {

    val userMenu = viewModel.uiState.collectAsState().value
    val context = LocalContext.current

    Log.i("leer", "view: ${userMenu.onTapSignIn}")

    SurfaceApp {
        MenuBody(
            userMenu = userMenu,
            onResultClick = { viewModel.onResultClick { onNextScreen(it) } },
            onLearnClick = { viewModel.onLearnClick { onNextScreen(it) } },
            onTestClick = { viewModel.onTestClick { onNextScreen(it) } },
            onEditUserNameClick = viewModel::onEditUserName,
            onEditUserNameDoneClick = viewModel::onEditUserNameDone,
            onUserNameTextChanged = viewModel::onNameChanged,
            onSignInWithGoogle = viewModel::onSignInWithGoogleAccount,
            onSignOutFromGoogle = viewModel::onSignOutFromAccount
        )
    }

    if (userMenu.showMessageIfUserIsLogged) {
        Toast.makeText(context, stringResource(R.string.login_success), Toast.LENGTH_SHORT).show()
        viewModel.onCloseShowingLoginMessage()
    }

    if (userMenu.showCloseSessionMessage) {
        Toast.makeText(context, stringResource(R.string.logout_success), Toast.LENGTH_SHORT).show()
        viewModel.onCloseShowingSignOutMessage()
    }

    val launcher =
        rememberLauncherForActivityResult(ActivityResultContracts.StartIntentSenderForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                // El usuario si permitio el acceso a su cuenta de google
                try {
                    val credentials =
                        viewModel.oneTapClient.getSignInCredentialFromIntent(result.data)
                    val googleIdToken = credentials.googleIdToken
                    val googleCredentials = GoogleAuthProvider.getCredential(googleIdToken, null)
                    viewModel.onCreateUserInFirebase(googleCredentials)
                } catch (it: ApiException) {
                    PrintLog.print("authScreen: result", it)
                }
            } else {
                // El usuario no permitio el acceso a su cuenta de google o cerro el dialog de acceso
                // Implementar logica necesaria
                PrintLog.print("authScreen: result: ", "No se dio permiso de acceso a la cuenta")
                Toast.makeText(
                    context,
                    "No se dio permiso de acceso a su cuenta",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

    fun launchIntent(signInResult: BeginSignInResult) {
        val intent = IntentSenderRequest.Builder(signInResult.pendingIntent.intentSender).build()
        launcher.launch(intent)
    }

    when (val oneTapSignInResponse = userMenu.onTapSignIn) {
        is UserAuthResponse.Loading -> {
            ProgressIndicatorApp()
        }
        is UserAuthResponse.Success -> oneTapSignInResponse.result?.let {
            LaunchedEffect(it) {
                launchIntent(it)
            }
        }
        is UserAuthResponse.Failure -> oneTapSignInResponse.exception?.let {
            LaunchedEffect(Unit) {
                if (it.message == "16: Cannot find a matching credential.") {
                    //No existe cuenta vinculada de google
                    //Logica necesaria
                    Log.i("leer", "Cuenta de google no disponible")
                    Toast.makeText(
                        context,
                        "No existe una cuenta de Google disponible en el dispositivo",
                        Toast.LENGTH_SHORT
                    ).show()
                    viewModel.resetOnTapSignIn()
                }
            }
        }
    }
}

@Composable
fun MenuBody(
    userMenu: MenuUiState,
    onLearnClick: () -> Unit,
    onTestClick: () -> Unit,
    onResultClick: () -> Unit,
    onEditUserNameClick: () -> Unit,
    onEditUserNameDoneClick: () -> Unit,
    onUserNameTextChanged: (String) -> Unit,
    onSignInWithGoogle: () -> Unit,
    onSignOutFromGoogle: () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        UserData(
            modifier = Modifier.padding(8.dp),
            name = userMenu.name,
            isLogin = userMenu.isSignIn,
            isEdit = userMenu.isEditName,
            onEditUserName = onEditUserNameClick,
            onEditUserNameDone = onEditUserNameDoneClick,
            onNameChanged = onUserNameTextChanged,
            onSignIn = onSignInWithGoogle,
            onSignOut = onSignOutFromGoogle
        )
        MenuActions(
            modifier = Modifier.fillMaxSize(),
            isLessonComplete = userMenu.isLessonComplete,
            isTestComplete = userMenu.isTestComplete,
            onLearnClick = onLearnClick,
            onTestClick = onTestClick,
            onResultClick = onResultClick
        )
    }
}

@Composable
fun UserData(
    modifier: Modifier = Modifier,
    name: String,
    isLogin: Boolean,
    isEdit: Boolean,
    onEditUserName: () -> Unit,
    onEditUserNameDone: () -> Unit,
    onNameChanged: (String) -> Unit,
    onSignIn: () -> Unit,
    onSignOut: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(
            text = stringResource(R.string.welcome),
            style = MaterialTheme.typography.h4
        )
        if (isEdit) {
            ShowEditName(
                name = name,
                onNameChanged = onNameChanged,
                onEditDone = onEditUserNameDone
            )
        } else {
            ShowUserName(
                isLogin = isLogin,
                name = name,
                onEditUserName = onEditUserName
            )
            if (isLogin) {
                ButtonSignOut(onSignOut = onSignOut)
            } else {
                ButtonSignIn(onSignIn = onSignIn)
            }
        }
    }
}

@Composable
private fun ButtonSignIn(onSignIn: () -> Unit) {
    ButtonApp(
        textButtonId = R.string.login_google,
        imageId = R.drawable.ic_google_logo,
        onClick = onSignIn
    )
}

@Composable
private fun ButtonSignOut(onSignOut: () -> Unit) {
    ButtonApp(
        textButtonId = R.string.sign_out,
        imageId = R.drawable.ic_sign_out,
        onClick = onSignOut
    )
}

@Composable
fun ShowEditName(
    name: String,
    onEditDone: () -> Unit,
    onNameChanged: (String) -> Unit
) {

    ShowUserMetadata(
        iconButton = Icons.Default.Save,
        descriptionId = R.string.edit_name,
        onClick = onEditDone
    ) {
        TextField(value = name, onValueChange = onNameChanged)
    }
}

@Composable
private fun ShowUserName(isLogin: Boolean, name: String, onEditUserName: () -> Unit) {
    if (isLogin) {
        Text(
            modifier = Modifier.padding(all = 8.dp),
            text = name,
            style = MaterialTheme.typography.h5
        )
    } else {
        ShowUserMetadata(
            iconButton = Icons.Default.Edit,
            descriptionId = R.string.edit_name,
            onClick = onEditUserName
        ) {
            Text(
                text = name,
                style = MaterialTheme.typography.h5
            )
        }
    }
}

@Composable
private fun ShowUserMetadata(
    iconButton: ImageVector,
    descriptionId: Int?,
    spacer: Dp = 4.dp,
    onClick: () -> Unit,
    contentItem: @Composable () -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        contentItem()
        Spacer(modifier = Modifier.width(width = spacer))
        IconButton(onClick = onClick) {
            Icon(
                imageVector = iconButton,
                contentDescription = descriptionId?.let { stringResource(it) }
            )
        }
    }
}

@Composable
fun MenuActions(
    modifier: Modifier = Modifier,
    isLessonComplete: Boolean,
    isTestComplete: Boolean,
    onLearnClick: () -> Unit,
    onTestClick: () -> Unit,
    onResultClick: () -> Unit
) {
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
            item {
                ButtonMenu(
                    title = R.string.learn_title,
                    image = R.drawable.learn,
                    isComplete = isLessonComplete,
                    onClick = onLearnClick,
                    onTestEnabled = true
                )
            }

            item {
                ButtonMenu(
                    title = R.string.test_title,
                    image = R.drawable.test,
                    isComplete = isTestComplete,
                    onClick = onTestClick,
                    onTestEnabled = isLessonComplete
                )
            }

            item {
                Button(
                    modifier = Modifier.padding(top = 16.dp),
                    onClick = onResultClick
                ) {
                    Text(
                        text = stringResource(R.string.my_result),
                        style = MaterialTheme.typography.h6
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ButtonMenu(
    @StringRes title: Int,
    @DrawableRes image: Int,
    isComplete: Boolean,
    onTestEnabled: Boolean = false,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        elevation = 8.dp,
        shape = MaterialTheme.shapes.small,
        enabled = onTestEnabled,
        modifier = Modifier.alpha(if (onTestEnabled) 1.0f else 0.8f)
    ) {
        Box(
            modifier = Modifier
                .height(180.dp)
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Image(
                modifier = Modifier.fillMaxWidth(),
                painter = painterResource(id = image),
                contentDescription = stringResource(id = title),
                contentScale = ContentScale.Crop
            )
            Text(
                modifier = Modifier.align(alignment = Alignment.BottomCenter),
                text = stringResource(id = title),
                style = MaterialTheme.typography.h4.copy(color = Color.White),
                fontWeight = FontWeight.Bold
            )
            Checkbox(
                modifier = Modifier.padding(start = 8.dp, top = 8.dp),
                checked = isComplete,
                onCheckedChange = null,
                enabled = false
            )
        }
    }
}