package com.aesuriagasalazar.competenciadigitalespracticum3_2.data.repositories

import com.aesuriagasalazar.competenciadigitalespracticum3_2.data.service.AuthService
import com.google.firebase.auth.AuthCredential
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val localDataRepository: LocalDataRepository,
    private val authService: AuthService
) {

    fun getUserName() = authService.getUserName().map {
        it.ifEmpty { localDataRepository.getUserName() }
    }

    suspend fun saveUserName(name: String) = localDataRepository.saveUserName(name)

    fun userLoginState() = authService.userAuthState()

    fun signInWithGoogle() = authService.oneTapSignInWithGoogle()

    fun firebaseSignInWithGoogle(googleCredentials: AuthCredential) =
        authService.createUserInFirebaseWithGoogleAccount(googleCredentials)

    fun signOutAccount() = authService.signOut()
}