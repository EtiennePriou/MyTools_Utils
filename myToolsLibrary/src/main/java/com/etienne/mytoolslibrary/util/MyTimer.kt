package com.etienne.mytoolslibrary.util

import android.os.CountDownTimer

fun myTimer(time: Long, foo: () -> Unit){
    val timer = object: CountDownTimer(time, 1000) {
        override fun onTick(millisUntilFinished: Long) {}
        override fun onFinish() {
            foo()
        }
    }
    timer.start()
}

fun myTimerObject(time: Long, foo: () -> Unit):CountDownTimer{
    return object: CountDownTimer(time, 1000) {
        override fun onTick(millisUntilFinished: Long) {}
        override fun onFinish() {
            foo()
        }
    }
}