package com.etienne.mytoolslibrary.extensions

import android.text.Editable

fun Editable?.toDouble(): Double{
    return try {
        this.toString().replace(",", ".").toDouble()
    }catch (e:Exception){
        0.0
    }
}