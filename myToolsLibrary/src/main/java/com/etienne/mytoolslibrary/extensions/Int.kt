package com.etienne.mytoolslibrary.extensions

import android.content.res.Resources
import kotlin.random.Random

fun getRandomInt(min:Int = 0, max: Int = 15000): Int{
    return Random.nextInt(min, max)
}

fun Int?.isNullOrZero():Boolean{
    return this == null || this == 0
}

fun Int.toDp(): Int = (this / Resources.getSystem().displayMetrics.density).toInt()
fun Int.toPx(): Int = (this * Resources.getSystem().displayMetrics.density).toInt()