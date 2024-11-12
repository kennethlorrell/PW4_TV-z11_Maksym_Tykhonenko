package com.deepdark.lab4.data

import kotlin.math.sqrt

// Обчислюємо початковий струм трифазного КЗ та загальний опір
fun calculateShortCircuitCurrent(
    nominalVoltage: Double,
    shortCircuitVoltage: Double,
    shortCircuitPower: Double,
    transformerPower: Double
): ShortCircuitCurrentResult {
    val xc = nominalVoltage * nominalVoltage / shortCircuitPower
    val xt = nominalVoltage * nominalVoltage / transformerPower * shortCircuitVoltage / 100
    val totalResistance = xc + xt

    val shortCircuitCurrent = nominalVoltage / (sqrt(3.0) * totalResistance)

    return ShortCircuitCurrentResult(
        totalResistance = totalResistance,
        shortCircuitCurrent = shortCircuitCurrent
    )
}