package com.mobilion.weatherapproute.android.fragment.dsl

import androidx.lifecycle.Lifecycle
import com.mobilion.weatherapproute.android.fragment.internal.FragmentContainerLifecycle
import com.mobilion.weatherapproute.android.fragment.internal.GenericFragmentContainerLifecycle

@FragmentRouterDsl
class GenericFragmentContainerLifecycleBuilder {

    @FragmentRouterDsl
    var attachOn: Lifecycle.Event = Lifecycle.Event.ON_RESUME

    @FragmentRouterDsl
    var detachOn: Lifecycle.Event = Lifecycle.Event.ON_PAUSE

    internal fun build(): FragmentContainerLifecycle.Factory =
        GenericFragmentContainerLifecycle.Factory(
            attachEvent = attachOn,
            detachEvent = detachOn
        )
}
