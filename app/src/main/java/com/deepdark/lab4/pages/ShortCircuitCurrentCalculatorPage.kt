package com.deepdark.lab4.pages

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.deepdark.lab4.data.ShortCircuitCurrentResult
import com.deepdark.lab4.data.calculateShortCircuitCurrent
import com.deepdark.lab4.utils.roundTo

@Composable
fun ShortCircuitCurrentCalculatorPage() {
    var nominalVoltage by remember { mutableStateOf("10.5") }
    var shortCircuitVoltage by remember { mutableStateOf("10.5") }
    var shortCircuitPower by remember { mutableStateOf("200") }
    var transformerPower by remember { mutableStateOf("6.3") }

    var result by remember { mutableStateOf<ShortCircuitCurrentResult?>(null) }  // Holds the calculation result

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text("Введіть параметри для розрахунку струму короткого замикання:")

        OutlinedTextField(
            value = nominalVoltage,
            onValueChange = { nominalVoltage = it },
            label = { Text("Номінальна напруга") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = shortCircuitVoltage,
            onValueChange = { shortCircuitVoltage = it },
            label = { Text("Напруга КЗ трансформатора") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = shortCircuitPower,
            onValueChange = { shortCircuitPower = it },
            label = { Text("Потужність короткого замикання") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = transformerPower,
            onValueChange = { transformerPower = it },
            label = { Text("Номінальна потужність трансформатора") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                result = calculateShortCircuitCurrent(
                    nominalVoltage.toDoubleOrNull() ?: 0.0,
                    shortCircuitVoltage.toDoubleOrNull() ?: 0.0,
                    shortCircuitPower.toDoubleOrNull() ?: 0.0,
                    transformerPower.toDoubleOrNull() ?: 0.0
                )
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Розрахувати")
        }

        Spacer(modifier = Modifier.height(16.dp))

        result?.let {
            Text("Загальний опір: ${it.totalResistance.roundTo(2)} Ом")
            Text("Початковий струм трифазного КЗ: ${it.shortCircuitCurrent.roundTo(1)} А")
        }
    }
}
