package com.aesuriagasalazar.competenciadigitalespracticum3_2.data.service.module

import android.app.Application
import android.content.Context
import com.aesuriagasalazar.competenciadigitalespracticum3_2.R
import com.aesuriagasalazar.competenciadigitalespracticum3_2.data.service.AuthService
import com.aesuriagasalazar.competenciadigitalespracticum3_2.data.service.impl.AuthServiceImpl
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class AuthModule {

    @Provides
    fun provideContext(
        app: Application
    ): Context = app.applicationContext

    @Provides
    fun firebaseInit(context: Context) = FirebaseApp.initializeApp(context)

    @Provides
    fun firebaseAuthInstance() = Firebase.auth

    @Provides
    fun provideOneTapClient(
        context: Context
    ) = Identity.getSignInClient(context)

    @Provides
    fun provideSignInRequest(
        app: Application
    ) = BeginSignInRequest.builder()
        .setGoogleIdTokenRequestOptions(
            BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                .setSupported(true)
                .setServerClientId(app.getString(R.string.client_id))
                .setFilterByAuthorizedAccounts(false)
                .build())
        .build()

    @Provides
    fun provideAuthService(
        auth: FirebaseAuth,
        oneTapClient: SignInClient,
        signInRequest: BeginSignInRequest
    ): AuthService {
        return AuthServiceImpl(auth, oneTapClient, signInRequest)
    }
}