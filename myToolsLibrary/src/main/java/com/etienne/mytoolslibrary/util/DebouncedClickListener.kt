package com.etienne.mytoolslibrary.util

import android.os.SystemClock
import android.view.View
import java.util.*

fun View.setOnDebouncedClickListener(onClick: (v: View) -> Unit) {
    setOnClickListener(DebouncingOnClickListener(onClick))
}

private const val minimumInterval = 750L

/**
 * Taken from Buttterknife
 */

class DebouncingOnClickListener(private val doClick: (v: View) -> Unit) : View.OnClickListener {
    private val lastClickMap: MutableMap<View, Long> = WeakHashMap()

    override fun onClick(v: View) {
        val previousClickTimestamp = lastClickMap[v]
        val currentTimestamp = SystemClock.uptimeMillis()

        if (previousClickTimestamp == null || currentTimestamp - previousClickTimestamp.toLong() > minimumInterval){
            doClick(v)
            lastClickMap[v] = currentTimestamp
        }
    }
}