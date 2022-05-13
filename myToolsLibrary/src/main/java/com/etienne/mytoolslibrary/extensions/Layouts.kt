package com.etienne.mytoolslibrary.extensions

import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.marginBottom
import androidx.core.view.marginLeft
import androidx.core.view.marginRight
import androidx.core.view.marginTop

fun LinearLayout.setMargin(left: Int, top: Int, right: Int, bottom: Int) : LinearLayout{
    val height = this.layoutParams.height
    val width = this.layoutParams.width
    val lp = LinearLayout.LayoutParams(width, height)
    lp.setMargins(left, top, right, bottom)
    this.layoutParams = lp
    return this
}

fun LinearLayout.setHeWi(height: Int?, width: Int?) : LinearLayout{
    val heightB = height ?: this.layoutParams.height
    val widthB = width ?: this.layoutParams.width
    val bottom = this.marginBottom
    val top = this.marginTop
    val left = this.marginLeft
    val right = this.marginRight
    val lp = LinearLayout.LayoutParams(widthB, heightB)
    lp.setMargins(left, top, right, bottom)
    this.layoutParams = lp
    return this
}

fun ConstraintLayout.setMargin(left: Int, top: Int, right: Int, bottom: Int) : ConstraintLayout{
    val height = this.layoutParams.height
    val width = this.layoutParams.width
    val lp = ConstraintLayout.LayoutParams(width, height)
    lp.setMargins(left, top, right, bottom)
    this.layoutParams = lp
    return this
}

fun ConstraintLayout.setHeWi(height: Int?, width: Int?) : ConstraintLayout{
    val heightB = height ?: this.layoutParams.height
    val widthB = width ?: this.layoutParams.width
    val bottom = this.marginBottom
    val top = this.marginTop
    val left = this.marginLeft
    val right = this.marginRight
    val lp = ConstraintLayout.LayoutParams(widthB, heightB)
    lp.setMargins(left, top, right, bottom)
    this.layoutParams = lp
    return this
}