package com.etienne.mytoolslibrary.extensions

import android.os.Looper

inline val Looper?.isMainLooper: Boolean
    get() = this == Looper.getMainLooper()

inline val Looper?.isNotMainLooper: Boolean
    get() = !this.isMainLooper