package com.deepdark.lab4.pages

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.deepdark.lab4.data.SubstationShortCircuitCurrentResult
import com.deepdark.lab4.data.calculateSubstationShortCircuitCurrents
import com.deepdark.lab4.utils.roundTo

@Composable
fun SubstationShortCircuitCalculatorPage() {
    var Ukmax by remember { mutableStateOf("11.1") }
    var Snom by remember { mutableStateOf("6.3") }
    var Uvn by remember { mutableStateOf("115") }
    var Unn by remember { mutableStateOf("11") }
    var Rcn by remember { mutableStateOf("10.65") }
    var Rcmin by remember { mutableStateOf("34.88") }
    var Xcn by remember { mutableStateOf("24.02") }
    var Xcmin by remember { mutableStateOf("65.68") }

    var result by remember { mutableStateOf<SubstationShortCircuitCurrentResult?>(null) }  // Holds the calculation result

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text("Введіть параметри підстанції для розрахунку струму короткого замикання:")

        OutlinedTextField(
            value = Ukmax,
            onValueChange = { Ukmax = it },
            label = { Text("Максимальний імпеданс КЗ (%)") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = Snom,
            onValueChange = { Snom = it },
            label = { Text("Номінальна потужність трансформатора (MVA)") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = Uvn,
            onValueChange = { Uvn = it },
            label = { Text("Високовольтна напруга трансформатора (кВ)") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = Unn,
            onValueChange = { Unn = it },
            label = { Text("Низьковольтна напруга трансформатора (кВ)") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = Rcn,
            onValueChange = { Rcn = it },
            label = { Text("Опір мережі в нормальному режимі (Ом)") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = Rcmin,
            onValueChange = { Rcmin = it },
            label = { Text("Опір мережі в мінімальному режимі (Ом)") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = Xcn,
            onValueChange = { Xcn = it },
            label = { Text("Реактивний опір мережі в нормальному режимі (Ом)") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = Xcmin,
            onValueChange = { Xcmin = it },
            label = { Text("Реактивний опір мережі в мінімальному режимі (Ом)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                result = calculateSubstationShortCircuitCurrents(
                    Ukmax.toDoubleOrNull() ?: 0.0,
                    Snom.toDoubleOrNull() ?: 0.0,
                    Uvn.toDoubleOrNull() ?: 0.0,
                    Unn.toDoubleOrNull() ?: 0.0,
                    Rcn.toDoubleOrNull() ?: 0.0,
                    Rcmin.toDoubleOrNull() ?: 0.0,
                    Xcn.toDoubleOrNull() ?: 0.0,
                    Xcmin.toDoubleOrNull() ?: 0.0
                )
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Розрахувати")
        }

        Spacer(modifier = Modifier.height(16.dp))

        result?.let {
            Text("Xt: ${it.Xt.roundTo(2)} Ом")
            Text("Xш в нормальному режимі приведений до 110кВ: ${it.Xsh.roundTo(2)} Ом")
            Text("Zш в нормальному режимі приведений до 110кВ: ${it.Zsh.roundTo(2)} Ом")
            Text("Xш в мінімальному режимі приведений до 110кВ: ${it.Xshmin.roundTo(2)} Ом")
            Text("Zш в мінімальному режимі приведений до 110кВ: ${it.Zshmin.roundTo(2)} Ом")
            Text("Струм трифазного КЗ в нормальному режимі приведений до 110кВ: ${it.I3.roundTo(2)} А")
            Text("Струм двофазного КЗ в нормальному режимі приведений до 110кВ: ${it.I2.roundTo(2)} А")
            Text("Струм трифазного КЗ в мінімальному режимі приведений до 110кВ: ${it.I3shmin.roundTo(2)} А")
            Text("Струм двофазного КЗ в мінімальному режимі приведений до 110кВ: ${it.I2shmin.roundTo(2)} А")
            Text("Коефіцієнт приведення: ${it.kpr.roundTo(2)}")
            Text("Rш в нормальному режимі приведений до 110кВ: ${it.Rshn.roundTo(2)} Ом")
            Text("Xш в нормальному режимі приведений до 110кВ: ${it.Xshn.roundTo(2)} Ом")
            Text("Zш в нормальному режимі приведений до 110кВ: ${it.Zshn.roundTo(2)} Ом")
            Text("Rш в мінімальному режимі: ${it.Rshnmin.roundTo(2)} Ом")
            Text("Xш в мінімальному режимі: ${it.Xshnmin.roundTo(2)} Ом")
            Text("Zш в мінімальному режимі: ${it.Zshnmin.roundTo(2)} Ом")
            Text("Струм трифазного КЗ в мінімальному режимі: ${it.I3shnmin.roundTo(2)} А")
            Text("Струм двофазного КЗ в мінімальному режимі: ${it.I2shnmin.roundTo(2)} А")
            Text("Релактанс відрізка: ${it.Rl.roundTo(2)} Ом")
            Text("Реактанс відрізка: ${it.Xl.roundTo(2)} Ом")
            Text("Ren в точці 10 в нормальному режимі: ${it.Ren.roundTo(2)} Ом")
            Text("Xen в точці 10 в нормальному режимі: ${it.Xen.roundTo(2)} Ом")
            Text("Zen в точці 10 в нормальному режимі: ${it.Zen.roundTo(2)} Ом")
            Text("Renmin в точці 10 в мінімальному режимі: ${it.Renmin.roundTo(2)} Ом")
            Text("Xenmin в точці 10 в мінімальному режимі: ${it.Xenmin.roundTo(2)} Ом")
            Text("Zenmin в точці 10 в мінімальному режимі: ${it.Zenmin.roundTo(2)} Ом")
            Text("Струм трифазного КЗ в точці 10 в нормальному режимі: ${it.I3ln.roundTo(2)} А")
            Text("Струм двофазного КЗ в точці 10 в нормальному режимі: ${it.I2ln.roundTo(2)} А")
            Text("Струм трифазного КЗ в точці 10 в мінімальному режимі: ${it.I3lnmin.roundTo(2)} А")
            Text("Струм двофазного КЗ в точці 10 в мінімальному режимі: ${it.I2lnmin.roundTo(2)} А")
        }
    }
}
