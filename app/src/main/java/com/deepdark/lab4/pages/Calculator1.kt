package com.deepdark.lab4.pages

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Calculator1() {
    var nominalVoltage by remember { mutableStateOf("10") }
    var shortCircuitCurrent by remember { mutableStateOf("2.5") }
    var shortCircuitDuration by remember { mutableStateOf("2.5") }
    var calculatedLoad by remember { mutableStateOf("1300") }
    var operatingDuration by remember { mutableStateOf("4000") }

    var result by remember { mutableStateOf<String?>(null) }

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text("Введіть параметри для вибору кабелю:")

        OutlinedTextField(
            value = nominalVoltage,
            onValueChange = { nominalVoltage = it },
            label = { Text("Номінальна напруга (кВ)") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = calculatedLoad,
            onValueChange = { calculatedLoad = it },
            label = { Text("Розрахункове навантаження (кВА)") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = shortCircuitCurrent,
            onValueChange = { shortCircuitCurrent = it },
            label = { Text("Струм короткого замикання (А)") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = shortCircuitDuration,
            onValueChange = { shortCircuitDuration = it },
            label = { Text("Час дії струму КЗ (с)") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = operatingDuration,
            onValueChange = { operatingDuration = it },
            label = { Text("Розрахункова тривалість роботи (год)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {},
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Розрахувати")
        }

        Spacer(modifier = Modifier.height(16.dp))

        result?.let {
            Text("Результати: $it")
        }
    }
}
