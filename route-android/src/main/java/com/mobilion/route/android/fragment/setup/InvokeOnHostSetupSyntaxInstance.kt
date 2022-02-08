package com.mobilion.weatherapproute.android.fragment.setup

import android.os.Bundle
import androidx.fragment.app.FragmentManager
import com.mobilion.weatherapproute.android.fragment.FragmentContainer
import com.mobilion.weatherapproute.android.fragment.FragmentRouter

internal interface InvokeOnHostSetupSyntaxInstance :
    FragmentRouterHost,
    FragmentRouterSetupSyntax,
    InvokeOnSaveInstanceStateSyntax {

    override fun FragmentRouter<*>.setup(
        savedInstanceState: Bundle?,
        containerId: Int,
        fragmentManager: FragmentManager
    ) {
        this.fragmentContainerLifecycle.setup(
            lifecycle, FragmentContainer(activity, fragmentManager, containerId)
        )
        invokeOnSaveInstanceState { outState -> saveState(outState) }
        if (savedInstanceState != null) {
            restoreState(savedInstanceState)
        }
    }
}
