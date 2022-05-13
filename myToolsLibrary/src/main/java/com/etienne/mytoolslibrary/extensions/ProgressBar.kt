package com.etienne.mytoolslibrary.extensions

import android.widget.ProgressBar
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat

fun ProgressBar.setTint(@ColorRes colorRes: Int){
    indeterminateDrawable.setTint(ContextCompat.getColor(this.context, colorRes))
}