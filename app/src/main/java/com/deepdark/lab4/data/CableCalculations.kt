package com.deepdark.lab4.data

import com.deepdark.lab4.utils.THERMAL_STABILITY_COEFFICIENT
import kotlin.math.sqrt

fun calculateCableSelection(
    nominalVoltage: Double,
    calculatedLoad: Double,
    shortCircuitCurrent: Double,
    shortCircuitDuration: Double,
    operatingDuration: Int
): CableSelectionResult {
    // Отримуємо економічну густину струму залежно від річного навантаження
    val economicCurrentDensity = getAluminumCurrentDensity(operatingDuration);

    // Вираховуємо розрахунковий струм для нормального та післяаварійних режимів
    val requiredCurrentCapacity = calculatedLoad / (2 * sqrt(3.0) * nominalVoltage)
    val emergencyCurrentCapacity = requiredCurrentCapacity * 2

    // Рахуємо економічний переріз
    val crossSectionArea = requiredCurrentCapacity / economicCurrentDensity

    // Обчислюємо переріз з термічною стійкістю
    val thermalStability = shortCircuitCurrent * sqrt(shortCircuitDuration) / THERMAL_STABILITY_COEFFICIENT

    return CableSelectionResult(
        requiredCurrentCapacity = requiredCurrentCapacity,
        emergencyCurrentCapacity = emergencyCurrentCapacity,
        crossSectionArea = crossSectionArea,
        thermalStability = thermalStability
    )
}

fun getAluminumCurrentDensity(usageHours: Int): Double {
    return when {
        usageHours > 5000 -> 1.2
        usageHours in 3000..5000 -> 1.4
        else -> 1.6
    }
}
