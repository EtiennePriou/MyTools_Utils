package com.etienne.mytoolslibrary.extensions

import androidx.lifecycle.LifecycleOwner
import androidx.viewbinding.ViewBinding

fun ViewBinding.tryLifecycleOwner(): LifecycleOwner?{
    return root.context.getFragmentManager()?.primaryNavigationFragment?.viewLifecycleOwner
}