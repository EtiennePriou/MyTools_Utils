package com.etienne.mytoolslibrary.extensions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.Transformations


fun <X, Y> LiveData<X>.map(transform: (X) -> Y): LiveData<Y> = Transformations.map(this, transform)

fun <T> LiveData<T>.observeOnce(lifecycleOwner: LifecycleOwner, observer: Observer<T>) {
    observe(lifecycleOwner, object : Observer<T> {
        override fun onChanged(t: T?) {
            observer.onChanged(t)
            removeObserver(this)
        }
    })
}
