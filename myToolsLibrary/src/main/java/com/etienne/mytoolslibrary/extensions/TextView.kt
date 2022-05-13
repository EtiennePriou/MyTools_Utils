package com.etienne.mytoolslibrary.extensions

import android.text.Editable
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.etienne.mytoolslibrary.R
import java.lang.ref.WeakReference

fun <T : CharSequence?> TextView.bindText(ld: LiveData<T>, lifecycleOwner: LifecycleOwner) {
    ld.observe(lifecycleOwner, Observer {
        text = it
    })
}
fun TextView.ensureText(text: CharSequence?) {
    if (text != this.text) {
        this.text = text
    }
}

inline fun <T : TextView> T.onGetTextLayout(crossinline onLayout: (TextView) -> Unit) {
    val ref = WeakReference(this)
    val getLayoutRunnable = object : Runnable {
        override fun run() {
            ref.get()?.let { tv ->
                val layout = tv.layout
                if (layout == null) {
                    tv.post(this)
                } else {
                    onLayout(tv)
                }
            }
        }
    }
    post(getLayoutRunnable)
}

fun TextView.counterText(min:Int, max:Int, text: Editable?, isOptional:Boolean = false): String?{
    if (min > max) {
        this.text = null
        return null
    }
    val length = text?.length?:0
    val maxLimit = (max * 0.8).toInt()
    return when{
        length == 0 && isOptional -> {
            this.text = "$length/$max"
            this.setTextColor(ContextCompat.getColor(this.rootView.context, R.color.neutral_100))
            null
        }
        length < min -> {
            this.text = "$length/$min"
            this.setTextColor(ContextCompat.getColor(this.rootView.context, R.color.negative))
            null
        }
        length in min until maxLimit -> {
            this.text = "$length"
            this.setTextColor(ContextCompat.getColor(this.rootView.context, R.color.positive))
            null
        }
        length <= max -> {
            this.text = "$length/$max"
            this.setTextColor(ContextCompat.getColor(this.rootView.context, R.color.positive))
            null
        }
        else -> {
            this.text = "$length/$max"
            this.setTextColor(ContextCompat.getColor(this.rootView.context, R.color.negative))
            "Vous avez dépassé la limite max de caractères"
        }
    }
}

fun TextView.setStartDrawable(@DrawableRes drawableRes: Int){
    val drawable = ContextCompat.getDrawable(this.context, drawableRes)
    this.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable, null, null, null)
}

fun TextView.setEndDrawable(@DrawableRes drawableRes: Int){
    val drawable = ContextCompat.getDrawable(this.context, drawableRes)
    this.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, drawable, null)
}

fun TextView.removeDrawable(){
    this.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, null, null)
}