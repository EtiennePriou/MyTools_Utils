@file:JvmName("ViewUtils")

package com.etienne.mytoolslibrary.extensions

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.annotation.SuppressLint
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.*
import android.view.View.*
import android.view.animation.Animation
import android.view.animation.Transformation
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.core.view.forEach
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.transition.ChangeBounds
import androidx.transition.Fade
import androidx.transition.TransitionManager
import androidx.transition.TransitionSet
import com.etienne.mytoolslibrary.util.Size
import com.etienne.mytoolslibrary.util.myTimerObject
import com.etienne.mytoolslibrary.util.setOnDebouncedClickListener


inline fun <T : View> T.onLayout(crossinline f: (T) -> Boolean) {
    if (f(this)) {
        return
    }
    viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
        override fun onGlobalLayout() {
            if (f(this@onLayout)) {
                viewTreeObserver.removeOnGlobalLayoutListener(this)
            }
        }
    })
}

inline fun <T : View> T.afterMeasured(crossinline f: T.() -> Unit) {
    if (measuredWidth > 0 && measuredHeight > 0) {
        f()
        return
    }
    viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
        override fun onGlobalLayout() {
            if (measuredWidth > 0 && measuredHeight > 0) {
                viewTreeObserver.removeOnGlobalLayoutListener(this)
                f()
            }
        }
    })
}

inline fun <T : View> T.afterMeasuredWidth(crossinline f: T.() -> Unit) {
    if (measuredWidth > 0) {
        f()
        return
    }
    viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
        override fun onGlobalLayout() {
            if (measuredWidth > 0) {
                viewTreeObserver.removeOnGlobalLayoutListener(this)
                f()
            }
        }
    })
}

inline fun <T : View> T.afterMeasuredHeight(crossinline f: T.() -> Unit) {
    if (measuredHeight > 0) {
        f()
        return
    }
    viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
        override fun onGlobalLayout() {
            if (measuredHeight > 0) {
                viewTreeObserver.removeOnGlobalLayoutListener(this)
                f()
            }
        }
    })
}

fun View.addPaddingTop(padding: Int) {
    setPaddingTop(paddingTop + padding)
}

fun View.setPaddingTop(padding: Int) {
    setPadding(paddingLeft, padding, paddingRight, paddingBottom)
}

fun View.dimension(@DimenRes id: Int) = context.resources.getDimensionPixelSize(id)
fun View.fadeOut(): ViewPropertyAnimator? {
    if (visibility == GONE) {
        return null
    }
    clearAnimation()
    animate().cancel()
    return animate().alpha(0f).withEndAction { visibility = GONE }
}

fun View.fadeIn(ignoreVisibility: Boolean = false, startDelayMs: Long = 0) {
    fadeIn(startDelayMs, ignoreVisibility, null)
}

fun View.fadeIn(startDelayMs: Long = 0, ignoreVisibility: Boolean = false, onEnd: (() -> Unit)?) {
    if (alpha == 1f && visibility == VISIBLE && !ignoreVisibility) return
    clearAnimation()
    animate().cancel()
    alpha = 0f
    visibility = VISIBLE
    animate().setStartDelay(startDelayMs).alpha(1f).withEndAction(onEnd)
}

fun View.dp(n: Int) = Size.dp(context, n)

fun View.color(@ColorRes resId: Int) = context.color(resId)

inline fun View.withAttributes(set: AttributeSet, attrs: IntArray, f: (arr: TypedArray) -> Unit) {
    val array = context.theme.obtainStyledAttributes(set, attrs, 0, 0)
    try {
        f(array)
    } finally {
        array.recycle()
    }
}

fun View.visibleIf(condition: Boolean, otherwise: Int) {
    if (condition) {
        if (visibility != VISIBLE) {
            visibility = VISIBLE
        }
    } else {
        if (visibility != otherwise) {
            visibility = otherwise
        }
    }
}

fun View.setPaddingBottom(padding: Int) {
    setPaddingRelative(paddingStart, paddingTop, paddingEnd, padding)
}

fun View.transitionVisible() {
    if (isVisible) return
    val parent = parent as? ViewGroup?
    if (parent != null) {
        TransitionManager.beginDelayedTransition(
            parent, TransitionSet()
                .addTransition(Fade())
                .addTransition(ChangeBounds())
                .setOrdering(TransitionSet.ORDERING_TOGETHER)
                .setDuration(300)
        )
        isVisible = true
    }
}

fun View.transitionGone() {
    if (isGone) return
    val parent = parent as? ViewGroup?
    if (parent != null) {
        TransitionManager.beginDelayedTransition(
            parent, TransitionSet()
                .addTransition(Fade())
                .addTransition(ChangeBounds())
                .setOrdering(TransitionSet.ORDERING_TOGETHER)
                .setDuration(300)
        )
        isGone = true
    }
}

fun View.bindVisible(ld: LiveData<Boolean>, lifecycleOwner: LifecycleOwner) {
    ld.observe(lifecycleOwner, Observer {
        isVisible = it
    })
}

fun View.bindEnabled(ld: LiveData<Boolean>, lifecycleOwner: LifecycleOwner) {
    ld.observe(lifecycleOwner, Observer {
        isEnabled = it
    })
}

fun View.ensureGone() {
    if (visibility != GONE) {
        visibility = GONE
    }
}

fun View.ensureVisible() {
    if (visibility != VISIBLE) {
        visibility = VISIBLE
    }
}

fun View.ensureGoneAnimate(animated: Boolean = false) {
    if (visibility != GONE) {
        if(animated) {
            this.animate().alpha(0.0f).setDuration(250).setListener(object :
                AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    super.onAnimationEnd(animation)
                    visibility = GONE
                }
            })
        }
        else {
            visibility = GONE
        }
    }
}


fun View.ensureVisibleAnimate(animated: Boolean = false) {
    if (visibility != VISIBLE) {
        this.alpha = 0.0f
        visibility = VISIBLE
        if(animated) {
            this.animate().alpha(1.0f).setDuration(250).setListener(object :
                AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    super.onAnimationEnd(animation)
                }
            })
        }
        else {
            visibility = VISIBLE
        }
    }
}

fun View.ensureInvisible() {
    if (visibility != INVISIBLE) {
        visibility = INVISIBLE
    }
}

fun View.onLongPressWithEvent(time: Long, onClick: (v: View, ev: MotionEvent) -> Unit){
    MyLongPressEvent(this, time, onClick)
}
fun View.onLongPress(time: Long, onClick: (v: View) -> Unit){
    MyLongPress(this, time, onClick)
}

@SuppressLint("ClickableViewAccessibility")
class MyLongPressEvent(view: View, time: Long, onClick: (v: View, ev: MotionEvent) -> Unit){
    init {
        var event: MotionEvent? = null
        var timed = false
        var canSendData = false

        val timer = myTimerObject(time){
            timed = false
            canSendData = true
            onClick(view, event!!) //Fire first Event
        }

        @SuppressLint("ClickableViewAccessibility")
        val longPressTl = OnTouchListener { _, event1 ->
            event = event1
            when (event1.action){
                MotionEvent.ACTION_DOWN -> {
                    if (!timed) {
                        timed = true
                        timer.start()
                    }
                }
                MotionEvent.ACTION_CANCEL -> {
                    if (timed) {
                        timer.cancel()
                        event = null
                        timed = false
                        canSendData = false
                    }
                }
                MotionEvent.ACTION_UP -> {
                    timer.cancel()
                    if (canSendData) {
                        onClick(view, event!!)
                    }
                    event = null
                    timed = false
                    canSendData = false
                }
            }
            if (canSendData) {
                onClick(view, event!!)
            }
            false
        }
        view.setOnTouchListener(longPressTl)
    }
}

class MyLongPress(view: View, time: Long, onClick: (v: View) -> Unit){
    init {
        var timed = false
        val timer = myTimerObject(time){ onClick(view) }

        @SuppressLint("ClickableViewAccessibility")
        val longPressTl = OnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_DOWN){
                if (!timed){
                    timed = true
                    timer.start()
                }
            }
            if (event.action == MotionEvent.ACTION_UP){
                timer.cancel()
                timed = false
            }
            false
        }
        view.setOnTouchListener(longPressTl)
    }
}

fun View.expand(onFinish: (() -> Unit)? = null) {
    measure(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
    val targetHeight = this.measuredHeight

    // Older versions of android (pre API 21) cancel animations for views with a height of 0.
    layoutParams.height = 1
    visibility = VISIBLE
    val a: Animation = object : Animation() {

        override fun applyTransformation(interpolatedTime: Float, t: Transformation?) {
            layoutParams.height = if (interpolatedTime == 1f) WindowManager.LayoutParams.WRAP_CONTENT else (targetHeight * interpolatedTime).toInt()
            requestLayout()
        }

        override fun willChangeBounds(): Boolean {
            return true
        }
    }

    // 1dp/ms
    a.duration = ((targetHeight) / context.resources.displayMetrics.density).toLong()
    animation = a
    a.doOnFinish(onFinish)
    startAnimation(a)
}

fun View.collapse(onFinish: (() -> Unit)? = null) {
    val initialHeight = measuredHeight
    val a: Animation = object : Animation() {
        override fun applyTransformation(interpolatedTime: Float, t: Transformation?) {
            if (interpolatedTime == 1f) {
                visibility = GONE
            } else {
                layoutParams.height = initialHeight - (initialHeight * interpolatedTime).toInt()
                requestLayout()
            }
        }

        override fun willChangeBounds(): Boolean {
            return true
        }

    }

    // 1dp/ms
    a.duration = (initialHeight / context.resources.displayMetrics.density).toLong()
    animation = a
    a.doOnFinish(onFinish)
    startAnimation(a)
}

fun View.applyCollapse(
    vararg btnViews: View,
    onCollapse: (() -> Unit)? = null,
    onExpend: (() -> Unit)? = null
){
    btnViews.forEach {
        it.setOnDebouncedClickListener {
            if (this.isVisible){
                this.collapse{
                    onCollapse?.let { it() }
                }
            }else{
                this.expand{
                    onExpend?.let { it() }
                }
            }
        }
    }
}

fun Animation.doOnFinish(onFinish: (() -> Unit)? = null){
    this.setAnimationListener(object : Animation.AnimationListener {
        override fun onAnimationStart(animation: Animation?) {}
        override fun onAnimationEnd(animation: Animation?) {
            onFinish?.let { it() }
        }

        override fun onAnimationRepeat(animation: Animation?) {}
    })
}

@SuppressLint("ClickableViewAccessibility")
fun View.enableContent(enable: Boolean){
    if (enable){
        this.alpha = 1f
    }else{
        this.alpha = 0.3f
    }
    this.isEnabled = enable

    fun disable(group: ViewGroup){
        group.forEach {
            it.isEnabled = enable

            if (it is ViewGroup) {
                disable(it)
            }
        }
    }
    if (this is ViewGroup){
        disable(this)
    }
}