package com.mobilion.weatherapproute.android.utils

import com.mobilion.weatherapproute.android.WrongThreadException


internal fun requireMainThread() {
    if (!isMainThread) {
        throw WrongThreadException(
            "Required main thread. Found: " +
                "${Thread.currentThread().id}: ${Thread.currentThread().name}"
        )
    }
}
