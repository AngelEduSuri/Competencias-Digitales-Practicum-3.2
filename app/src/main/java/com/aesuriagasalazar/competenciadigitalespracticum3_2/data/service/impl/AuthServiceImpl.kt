package com.aesuriagasalazar.competenciadigitalespracticum3_2.data.service.impl

import com.aesuriagasalazar.competenciadigitalespracticum3_2.data.service.AuthService
import com.aesuriagasalazar.competenciadigitalespracticum3_2.domain.UserAuthResponse
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthServiceImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val oneTapClient: SignInClient,
    private val beginSignInRequest: BeginSignInRequest
) : AuthService {

    override fun userAuthState(): Flow<Boolean> = flow {
        emit(auth.currentUser != null)
    }

    override fun getUserName(): Flow<String> = flow {
        val name = auth.currentUser?.displayName
        emit(name ?: "")
    }

    override fun oneTapSignInWithGoogle() = flow {
        emit(UserAuthResponse.Loading)
        val task = oneTapClient.beginSignIn(beginSignInRequest).await()
        emit(UserAuthResponse.Success(task))
    }.catch { emit(UserAuthResponse.Failure(Exception(it))) }

    override fun createUserInFirebaseWithGoogleAccount(credential: AuthCredential) = flow {
        auth.signInWithCredential(credential).await()
        emit(true)
    }.catch { emit(false) }

    override fun signOut(): Flow<Boolean> = flow {
        auth.signOut()
        oneTapClient.signOut().await()
        emit(true)
    }.catch { emit(false) }
}