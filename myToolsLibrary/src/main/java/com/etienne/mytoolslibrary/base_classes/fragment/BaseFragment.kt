package com.etienne.mytoolslibrary.base_classes.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import timber.log.Timber

abstract class BaseFragment : Fragment() {
    private var canSubscribeDestroyView = false
    private var canSubscribeDestroy = false

    val bundle = Bundle()

    override fun startActivity(intent: Intent) {
        if (isAdded) {
            Timber.tag(this.javaClass.simpleName).i("startActivity: %s", intent.toUri(0))
            super.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Timber.tag(javaClass.simpleName).i("onCreate")
        super.onCreate(savedInstanceState)
        canSubscribeDestroyView = true
        canSubscribeDestroy = true
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        Timber.tag(javaClass.simpleName).i("onCreateView")
        canSubscribeDestroyView = true
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onStart() {
        Timber.tag(javaClass.simpleName).i("onStart")

        super.onStart()
    }

    override fun onResume() {
        super.onResume()

    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroyView() {
        Timber.tag(javaClass.simpleName).i("onDestroyView START")
        super.onDestroyView()
        Timber.tag(javaClass.simpleName).i("onDestroyView END")
    }

    override fun onDestroy() {
        Timber.tag(javaClass.simpleName).i("onDestroy")
        super.onDestroy()
    }
}
