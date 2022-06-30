package com.aesuriagasalazar.competenciadigitalespracticum3_2.data.service

import com.aesuriagasalazar.competenciadigitalespracticum3_2.model.UserAuthResponse
import com.google.android.gms.auth.api.identity.BeginSignInResult
import com.google.firebase.auth.AuthCredential
import kotlinx.coroutines.flow.Flow

interface AuthService {
    fun userAuthState(): Flow<Boolean>
    fun getUserName(): Flow<String>
    fun oneTapSignInWithGoogle(): Flow<UserAuthResponse<BeginSignInResult>>
    fun createUserInFirebaseWithGoogleAccount(credential: AuthCredential): Flow<Boolean>
    fun signOut(): Flow<Boolean>
}