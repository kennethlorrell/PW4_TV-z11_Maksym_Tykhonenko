package com.deepdark.lab4.pages

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.deepdark.lab4.data.CableSelectionResult
import com.deepdark.lab4.data.calculateCableSelection
import com.deepdark.lab4.utils.roundTo

@Composable
fun CableCalculatorPage() {
    var nominalVoltage by remember { mutableStateOf("10") }
    var shortCircuitCurrent by remember { mutableStateOf("2500") }
    var shortCircuitDuration by remember { mutableStateOf("2.5") }
    var calculatedLoad by remember { mutableStateOf("1300") }
    var operatingDuration by remember { mutableStateOf("4000") }

    var result by remember { mutableStateOf<CableSelectionResult?>(null) }

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
            label = { Text("Номінальна напруга") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = calculatedLoad,
            onValueChange = { calculatedLoad = it },
            label = { Text("Розрахункове навантаження") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = shortCircuitCurrent,
            onValueChange = { shortCircuitCurrent = it },
            label = { Text("Струм короткого замикання") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = shortCircuitDuration,
            onValueChange = { shortCircuitDuration = it },
            label = { Text("Час дії струму КЗ") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = operatingDuration,
            onValueChange = { operatingDuration = it },
            label = { Text("Розрахункова тривалість роботи") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val voltage = nominalVoltage.toDoubleOrNull() ?: 0.0
                val load = calculatedLoad.toDoubleOrNull() ?: 0.0
                val current = shortCircuitCurrent.toDoubleOrNull() ?: 0.0
                val sCDuration = shortCircuitDuration.toDoubleOrNull() ?: 0.0
                val oDuration = operatingDuration.toIntOrNull() ?: 0

                result = calculateCableSelection(voltage, load, current, sCDuration, oDuration)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Розрахувати")
        }

        Spacer(modifier = Modifier.height(16.dp))

        result?.let {
            Text("Розрахунковий струм для нормального режима: ${it.requiredCurrentCapacity.roundTo(1)} А")
            Text("Розрахунковий струм для післяаварійного режима: ${it.emergencyCurrentCapacity.roundTo(1)} А")
            Text("Економічний переріз кабелю: ${it.crossSectionArea.roundTo(1)} мм²")
            Text("Переріз кабелю за термічною стійкістю: ${it.thermalStability.roundTo(1)} мм²")
        }
    }
}
