package com.etienne.mytoolslibrary.base_classes

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import timber.log.Timber

abstract class BaseActivity : AppCompatActivity() {


    private var canSubscribeDestroy = false
    override fun startActivity(intent: Intent) {
        Timber.tag(this.javaClass.simpleName).i("startActivity: %s", intent.toUri(0))
        super.startActivity(intent)
    }

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        Timber.tag(javaClass.simpleName).i("onCreate | savedInstanceState: %s | %s", savedInstanceState, intent.toUri(0))
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.setDecorFitsSystemWindows(true)
        }
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE or WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)
        super.onCreate(savedInstanceState)
        canSubscribeDestroy = true
    }

    override fun onResume() {
        Timber.tag(this.javaClass.simpleName).i("onResume")
        super.onResume()
    }

    override fun onStop() {
        Timber.tag(javaClass.simpleName).i("onStop")
        super.onStop()
    }

    override fun onDestroy() {
        Timber.tag(javaClass.simpleName).i("onDestroy")
        super.onDestroy()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    val context: Context
        get() = this
}
