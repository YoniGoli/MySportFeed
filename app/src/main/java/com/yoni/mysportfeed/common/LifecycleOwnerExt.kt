package com.yoni.mysportfeed.common

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner

fun LifecycleOwner.whenPaused(cb: LifecycleOwner.() -> Unit) {
    lifecycle.addObserver(object : DefaultLifecycleObserver {
        override fun onPause(owner: LifecycleOwner) {
            cb()
        }

        override fun onDestroy(owner: LifecycleOwner) {
            lifecycle.removeObserver(this)
        }
    })
}