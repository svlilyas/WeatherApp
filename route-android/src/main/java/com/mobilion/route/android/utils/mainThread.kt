package com.mobilion.weatherapproute.android.utils

internal inline fun <T> mainThread(crossinline action: () -> T) {
    if (isMainThread) {
        action()
    } else {
        mainThreadHandler.post { action() }
    }
}
