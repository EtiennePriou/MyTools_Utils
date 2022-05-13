package com.etienne.mytoolslibrary.util

import android.content.Context

object Size {
    fun dp(context: Context, dp: Int): Int {
        val density = context.resources.displayMetrics.density
        return (dp * density + 0.5f).toInt()
    }
}