package com.aesuriagasalazar.competenciadigitalespracticum3_2.ui.screens.menu

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aesuriagasalazar.competenciadigitalespracticum3_2.data.repositories.UserRepository
import com.aesuriagasalazar.competenciadigitalespracticum3_2.data.sources.StaticDataSource
import com.aesuriagasalazar.competenciadigitalespracticum3_2.model.UserActions
import com.aesuriagasalazar.competenciadigitalespracticum3_2.model.UserAuthResponse
import com.google.android.gms.auth.api.identity.BeginSignInResult
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.AuthCredential
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(
    private val userRepository: UserRepository,
    staticDataSource: StaticDataSource,
    val oneTapClient: SignInClient
) :
    ViewModel() {

    private val _uiState = MutableStateFlow(
        MenuUiState(
            buttons = staticDataSource.menuUser
        )
    )

    val uiState: StateFlow<MenuUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            updateUserState()
        }
    }

    private fun updateUserState() = viewModelScope.launch {
        userRepository.getUserName().collect { name ->
            _uiState.update { it.copy(name = name) }
        }
        userRepository.userLoginState().collect { authState ->
            _uiState.update { it.copy(isSignIn = authState) }
        }
    }

    fun onButtonAction(button: UserActions, onLearnClick: () -> Unit, onTestClick: () -> Unit) {
        if (button == _uiState.value.buttons[0]) {
            onLearnClick()
        } else {
            onTestClick()
        }
    }

    fun onSignInWithGoogleAccount() = viewModelScope.launch {
        userRepository.signInWithGoogle().collect { authResponse ->
            _uiState.update {
                it.copy(onTapSignIn = authResponse)
            }
        }
    }

    fun onCreateUserInFirebase(googleCredentials: AuthCredential) = viewModelScope.launch {
        userRepository.firebaseSignInWithGoogle(googleCredentials).collect { isLogged ->
            _uiState.update { it.copy(showMessageIfUserIsLogged = isLogged) }
        }
        updateUserState()
    }

    fun onSignOutFromAccount() = viewModelScope.launch {
        userRepository.signOutAccount().collect { closeSession ->
            _uiState.update { it.copy(showCloseSessionMessage = closeSession) }
        }
        updateUserState()
    }

    fun onEditUserName() {
        _uiState.update { it.copy(isEditName = true) }
    }

    fun onNameChanged(name: String) {
        _uiState.update { it.copy(name = name) }
    }

    fun onEditUserNameDone() {
        viewModelScope.launch {
            userRepository.saveUserName(_uiState.value.name)
            _uiState.update { it.copy(isEditName = false) }
        }
    }

    fun onCloseShowingLoginMessage() {
        _uiState.update { it.copy(showMessageIfUserIsLogged = false) }
    }

    fun onCloseShowingSignOutMessage() {
        _uiState.update { it.copy(showCloseSessionMessage = false) }
    }
}

data class MenuUiState(
    val name: String = "",
    val isSignIn: Boolean = false,
    val isEditName: Boolean = false,
    val buttons: List<UserActions> = emptyList(),
    val onTapSignIn: UserAuthResponse<BeginSignInResult> = UserAuthResponse.Success(null),
    val showCloseSessionMessage: Boolean = false,
    val showMessageIfUserIsLogged: Boolean = false
)


