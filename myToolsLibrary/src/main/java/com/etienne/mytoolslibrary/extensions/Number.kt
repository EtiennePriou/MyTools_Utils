package com.etienne.mytoolslibrary.extensions

import java.text.DecimalFormat
import kotlin.math.floor
import kotlin.math.log10
import kotlin.math.pow

fun Number.compact(): String? {
    val suffix = charArrayOf(' ', 'k', 'M', 'B', 'T', 'P', 'E')
    val numValue = this.toLong()
    val value = floor(log10(numValue.toDouble())).toInt()
    val base = value / 3
    return if (value >= 3 && base < suffix.size) {
        DecimalFormat("#0.0").format(numValue / 10.0.pow((base * 3).toDouble())) + suffix[base]
    } else {
        DecimalFormat("#,##0").format(numValue)
    }
}

fun Int.toStringFormatted(): String{
    return String.format("%,d", this)
}

fun Number.toStringCurrency(showSymbol: Boolean = false):String{
    var str = String.format("%.2f", this)
    if (showSymbol) str += "€"
    return str
}