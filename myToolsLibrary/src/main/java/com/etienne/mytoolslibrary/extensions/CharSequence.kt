package com.etienne.mytoolslibrary.extensions

import android.text.TextUtils

val CharSequence?.trimmedLength: Int get() {
    if (this == null) return 0
    return TextUtils.getTrimmedLength(this)
}
