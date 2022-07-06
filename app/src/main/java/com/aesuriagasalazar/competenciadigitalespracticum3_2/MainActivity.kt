package com.aesuriagasalazar.competenciadigitalespracticum3_2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.SideEffect
import com.aesuriagasalazar.competenciadigitalespracticum3_2.ui.navigation.NavigationApp
import com.aesuriagasalazar.competenciadigitalespracticum3_2.ui.theme.AppTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                val statusBarColor = MaterialTheme.colors.primary
                val systemUiController = rememberSystemUiController()
                SideEffect {
                    systemUiController.setSystemBarsColor(
                        color = statusBarColor
                    )
                }
                NavigationApp()
            }
        }
    }
}

