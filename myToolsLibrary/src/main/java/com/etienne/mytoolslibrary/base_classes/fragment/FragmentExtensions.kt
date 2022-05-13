@file:JvmName("FragmentExtensions")
package com.etienne.mytoolslibrary.base_classes.fragment

import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

fun <T : Parcelable> Fragment.storeParams(params: T): T {
    if (isStateSaved) return params
    val args = arguments ?: Bundle(1)
    args.putParcelable("PARAMS", params)
    arguments = args
    return params
}

fun <T : Parcelable> Fragment.retrieveParams(): T {
    return arguments!!.getParcelable("PARAMS")!!
}

class StateHolderViewModel : ViewModel() {
    var state: Any? = null
}

fun <T> Fragment.retainedState(initialState: T): Lazy<T> {
    return lazy(LazyThreadSafetyMode.NONE) {
        useState(initialState)
    }
}

fun <T> Fragment.useState(initialState: T): T {
    val viewModel = ViewModelProvider(this).get(StateHolderViewModel::class.java)

    if (viewModel.state == null) {
        viewModel.state = initialState
    }

    @Suppress("UNCHECKED_CAST")
    return viewModel.state as T
}
