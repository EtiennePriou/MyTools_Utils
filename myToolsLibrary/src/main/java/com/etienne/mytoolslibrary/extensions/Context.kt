package com.etienne.mytoolslibrary.extensions

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager

fun Context.drawable(@DrawableRes id: Int) = AppCompatResources.getDrawable(this, id)!!
fun Context.color(@ColorRes id: Int) = ContextCompat.getColor(this, id)

tailrec fun Context.getActivity(): Activity? = this as? Activity ?: (this as? ContextWrapper)?.baseContext?.getActivity()

tailrec fun Context.getFragmentActivity(): FragmentActivity? = this as? FragmentActivity ?: (this as? ContextWrapper)?.baseContext?.getFragmentActivity()

fun Context.getFragmentManager(): FragmentManager? {
    return getFragmentActivity()?.supportFragmentManager
}