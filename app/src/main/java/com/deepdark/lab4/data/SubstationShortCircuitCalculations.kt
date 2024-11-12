package com.deepdark.lab4.data

import com.deepdark.lab4.utils.LINE_LENGTH
import com.deepdark.lab4.utils.LINE_REACTANCE
import com.deepdark.lab4.utils.LINE_RESISTANCE
import kotlin.math.pow
import kotlin.math.sqrt

fun calculateSubstationShortCircuitCurrents(
    Ukmax: Double,
    Snom: Double,
    Uvn: Double,
    Unn: Double,
    Rcn: Double,
    Rcmin: Double,
    Xcn: Double,
    Xcmin: Double
): SubstationShortCircuitCurrentResult {
    // Рахуємо реактанс силового трансформатора
    val Xt = Ukmax * Uvn.pow(2) / (Snom * 100)

    // Опори на шинах 10кВ в нормальному та мінімальному режимах, що приведені до 110кВ
    val Xsh = Xcn + Xt
    val Zsh = sqrt(Rcn.pow(2) + Xsh.pow(2))
    val Xshmin = Xcmin + Xt
    val Zshmin = sqrt(Xcmin.pow(2) + Xshmin.pow(2))

    // Струми трифазного та двофазного КЗ на шинах 10кВ в нормальному та мінімальному режимах, що приведені до 110кВ
    val I3 = Uvn * 1000 / (sqrt(3.0) * Zsh)
    val I2 = I3 * sqrt(3.0) / 2
    val I3shmin = Uvn * 1000 / (sqrt(3.0) * Zshmin)
    val I2shmin = I3shmin * sqrt(3.0) / 2

    // Коефіцієнт приведення
    val kpr = Unn.pow(2) / Uvn.pow(2)

    // Опори на шинах 10кВ в нормальному та мінімальному режимах
    val Rshn = Rcn * kpr
    val Xshn = Xsh * kpr
    val Zshn = sqrt(Rshn.pow(2) + Xshn.pow(2))
    val Rshnmin = Rcmin * kpr
    val Xshnmin = Xshmin * kpr
    val Zshnmin = sqrt(Rshnmin.pow(2) + Xshnmin.pow(2))

    // Дійсні струми трифазного та двофазного КЗ на шинах 10кВ в нормальному та мінімальному режимах
    val I3shnmin = Unn * 1000 / (sqrt(3.0) * Zshnmin)
    val I2shnmin = I3shnmin * sqrt(3.0) / 2

    // Опори в точці 10 в нормальному та мінімальному режимах
    val Rl = LINE_LENGTH * LINE_RESISTANCE
    val Xl = LINE_LENGTH * LINE_REACTANCE
    val Ren = Rl + Rshn
    val Xen = Xl + Xshn
    val Zen = sqrt(Ren.pow(2) + Xen.pow(2))
    val Renmin = Rl + Rshnmin
    val Xenmin = Xl + Xshnmin
    val Zenmin = sqrt(Renmin.pow(2) + Xenmin.pow(2))

    // Струми трифазного та двофазного КЗ в точці 10 в нормальному та мінімальному режимах
    val I3ln = Unn * 1000 / (sqrt(3.0) * Zen)
    val I2ln = I3ln * sqrt(3.0) / 2
    val I3lnmin = Unn * 1000 / (sqrt(3.0) * Zenmin)
    val I2lnmin = I3lnmin * sqrt(3.0) / 2

    return SubstationShortCircuitCurrentResult(
        Xt = Xt,
        Xsh = Xsh,
        Zsh = Zsh,
        Xshmin = Xshmin,
        Zshmin = Zshmin,
        I3 = I3,
        I2 = I2,
        I3shmin = I3shmin,
        I2shmin = I2shmin,
        kpr = kpr,
        Rshn = Rshn,
        Xshn = Xshn,
        Zshn = Zshn,
        Rshnmin = Rshnmin,
        Xshnmin = Xshnmin,
        Zshnmin = Zshnmin,
        I3shnmin = I3shnmin,
        I2shnmin = I2shnmin,
        Rl = Rl,
        Xl = Xl,
        Ren = Ren,
        Xen = Xen,
        Zen = Zen,
        Renmin = Renmin,
        Xenmin = Xenmin,
        Zenmin = Zenmin,
        I3ln = I3ln,
        I2ln = I2ln,
        I3lnmin = I3lnmin,
        I2lnmin = I2lnmin
    )
}