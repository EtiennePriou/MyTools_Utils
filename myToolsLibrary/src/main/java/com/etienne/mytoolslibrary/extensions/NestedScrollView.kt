package com.etienne.mytoolslibrary.extensions

import androidx.core.widget.NestedScrollView

fun NestedScrollView.onBottomReached(action : () -> Unit){
    this.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener{v, _, scrollY, _, _ ->
        val absoluteY = v.measuredHeight - v.getChildAt(0).measuredHeight
        if (scrollY == absoluteY || scrollY == (absoluteY * -1)) {
            action()
        }
    })
}