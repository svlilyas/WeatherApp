package com.mobilion.weatherapproute.android.fragment.internal

import androidx.lifecycle.Lifecycle
import com.mobilion.weatherapproute.android.fragment.FragmentContainer
import com.mobilion.weatherapproute.android.fragment.FragmentRouter

internal interface FragmentContainerLifecycle {

    interface Factory {
        operator fun invoke(router: FragmentRouter<*>): FragmentContainerLifecycle
    }

    fun setup(lifecycle: Lifecycle, container: FragmentContainer)
}
