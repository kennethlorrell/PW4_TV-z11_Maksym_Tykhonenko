package com.deepdark.lab4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.deepdark.lab4.components.TopAppBar
import com.deepdark.lab4.pages.Calculator1
import com.deepdark.lab4.pages.Calculator2
import com.deepdark.lab4.pages.Calculator3
import com.deepdark.lab4.ui.theme.Lab4Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab4Theme {
                val navController = rememberNavController()

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = { TopAppBar(navController) }
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = "calculator1",
                        Modifier.padding(innerPadding)
                    ) {
                        composable("calculator1") { Calculator1() }
                        composable("calculator2") { Calculator2() }
                        composable("calculator3") { Calculator3() }
                    }
                }
            }
        }
    }
}
