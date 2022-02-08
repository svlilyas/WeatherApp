package com.mobilion.weatherapproute.android.fragment.setup

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Lifecycle

internal interface FragmentRouterHost {
    val lifecycle: Lifecycle
    val activity: FragmentActivity
}
