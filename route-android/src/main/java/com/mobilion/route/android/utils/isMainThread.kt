package com.mobilion.weatherapproute.android.utils

import android.os.Looper

internal val isMainThread: Boolean get() = Looper.getMainLooper() == Looper.myLooper()
