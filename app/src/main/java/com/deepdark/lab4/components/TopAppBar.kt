package com.deepdark.lab4.components

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(navController: NavController) {
    CenterAlignedTopAppBar(
        title = { Text("") },
        actions = {
            TextButton(onClick = { navController.navigate("CableCalculator") }) {
                Text("Завдання 1")
            }
            TextButton(onClick = { navController.navigate("ShortCircuitCurrentCalculator") }) {
                Text("Завдання 2")
            }
            TextButton(onClick = { navController.navigate("calculator3") }) {
                Text("Завдання 3")
            }
        }
    )
}