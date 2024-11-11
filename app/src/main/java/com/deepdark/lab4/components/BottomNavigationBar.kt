package com.deepdark.lab4.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.deepdark.lab4.utils.currentRoute

@Composable
fun BottomNavigationBar(navController: NavController) {
    NavigationBar {
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Home, contentDescription = "Page 1") },
            label = { Text("Calculator 1") },
            selected = currentRoute(navController) == "calculator1",
            onClick = { navController.navigate("calculator1") }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Settings, contentDescription = "Calculator 2") },
            label = { Text("Calculator 2") },
            selected = currentRoute(navController) == "calculator2",
            onClick = { navController.navigate("calculator2") }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Info, contentDescription = "Calculator 3") },
            label = { Text("Calculator 3") },
            selected = currentRoute(navController) == "calculator3",
            onClick = { navController.navigate("calculator3") }
        )
    }
}