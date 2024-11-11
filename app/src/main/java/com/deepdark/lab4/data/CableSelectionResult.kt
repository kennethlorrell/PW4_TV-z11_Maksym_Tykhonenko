package com.deepdark.lab4.data

data class CableSelectionResult(
    val requiredCurrentCapacity: Double,
    val emergencyCurrentCapacity: Double,
    val crossSectionArea: Double,
    val thermalStability: Double
)