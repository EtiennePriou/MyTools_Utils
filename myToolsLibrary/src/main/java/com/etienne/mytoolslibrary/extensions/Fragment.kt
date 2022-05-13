package com.etienne.mytoolslibrary.extensions

import android.content.Intent
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.ColorRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

fun Fragment.color(@ColorRes id: Int) = context!!.color(id)

fun Fragment.activityForResult(then:(ActivityResult) -> Unit):ActivityResultLauncher<Intent>{
    return registerForActivityResult(ActivityResultContracts.StartActivityForResult(), then)
}

fun FragmentActivity.activityForResult(then:(ActivityResult) -> Unit):ActivityResultLauncher<Intent>{
    return registerForActivityResult(ActivityResultContracts.StartActivityForResult(), then)
}