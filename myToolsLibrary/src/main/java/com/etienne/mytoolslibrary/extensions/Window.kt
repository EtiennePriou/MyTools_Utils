package com.etienne.mytoolslibrary.extensions

import android.app.Activity
import android.os.Build
import android.util.DisplayMetrics
import android.view.Window
import androidx.annotation.ColorInt
import androidx.core.view.WindowInsetsControllerCompat

@JvmOverloads
fun colorStatusBar(window: Window, @ColorInt color: Int, isLight: Boolean = true) {
    val decorView = window.decorView
    val wic = WindowInsetsControllerCompat(window, decorView)
    wic.isAppearanceLightStatusBars = isLight

    window.statusBarColor = color
}

fun colorizeNavigationBar(window: Window, @ColorInt color: Int, isLight: Boolean = true) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1) {
        if (window.navigationBarColor != color) {
            val decorView = window.decorView
            val wic = WindowInsetsControllerCompat(window, decorView)
            wic.isAppearanceLightNavigationBars = isLight

            window.navigationBarColor = color
        }
    }
}

fun getDisplayMetrics(activity: Activity): DisplayMetrics{
    return activity.applicationContext.resources.displayMetrics
}
