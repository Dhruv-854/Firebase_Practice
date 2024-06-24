package com.dhruv.firebasepractice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.dhruv.firebasepractice.presentation.navigation.NavigationController
import com.dhruv.firebasepractice.ui.theme.FirebasePracticeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FirebasePracticeTheme {
                NavigationController()
            }
        }
    }
}
