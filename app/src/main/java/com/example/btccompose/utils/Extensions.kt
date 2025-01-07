package com.example.btccompose.utils

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale

fun String.formatVolume(): String {
    return this.toDouble().let {
        val decimalFormat =
            DecimalFormat("#,##0.##", DecimalFormatSymbols(Locale.getDefault()).apply {
                groupingSeparator = ','
                decimalSeparator = '.'
            })
        decimalFormat.format(it)
    }
}


