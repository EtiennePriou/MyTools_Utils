package com.etienne.mytoolslibrary.extensions

import android.content.res.Resources
import androidx.annotation.ColorRes
import androidx.core.content.res.ResourcesCompat

fun Resources.color(@ColorRes color: Int): Int = ResourcesCompat.getColor(this, color, null)
