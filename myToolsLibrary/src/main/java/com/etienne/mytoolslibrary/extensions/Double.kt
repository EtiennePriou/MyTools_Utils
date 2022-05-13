package com.etienne.mytoolslibrary.extensions

import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*
import kotlin.math.roundToInt


fun Double.decimalFormat(): String = DecimalFormat.getInstance().format(this)


fun Double?.toAvString():String{
    return when (this) {
        null, 0.0 -> {
            "-"
        }
        this.roundToInt().toDouble() -> {
            this.roundToInt().toString()
        }
        else -> {
            DecimalFormat("0.#").format(this).replace(".", ",")
        }
    }
}

fun Double.toCashMoney():String{
    return NumberFormat.getCurrencyInstance(Locale.FRANCE).format(this)
}
