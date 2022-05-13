package com.etienne.mytoolslibrary.extensions

import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import com.google.android.material.switchmaterial.SwitchMaterial

fun SwitchMaterial.setTint(@ColorRes colorRes: Int, @ColorRes colorResLight: Int){
    thumbTintList = ContextCompat.getColorStateList(context, colorRes)
    trackTintList = ContextCompat.getColorStateList(context, colorResLight)
}