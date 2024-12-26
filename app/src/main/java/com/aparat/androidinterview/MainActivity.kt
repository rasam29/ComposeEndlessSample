package com.aparat.androidinterview

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.aparat.androidinterview.ui.navigation.MainNavGraph
import com.aparat.androidinterview.ui.theme.AparatAndroidInterviewTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AparatAndroidInterviewTheme {
                val navController = rememberNavController()
                MainNavGraph(navController)
            }
        }
    }
}
