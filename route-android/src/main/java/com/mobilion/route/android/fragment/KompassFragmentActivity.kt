package com.mobilion.weatherapproute.android.fragment

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import com.mobilion.weatherapproute.android.fragment.setup.ActivityFragmentRouterSetupSyntax

interface KompassFragmentActivity :
    FragmentActivityExtension,
    PopRetainRootImmediateSyntax,
    PopRetainRootImmediateOrFinishSyntax {

    fun FragmentRouter<*>.setup(
        savedInstanceState: Bundle?,
        @IdRes containerId: Int,
        fragmentManager: FragmentManager
    ) {
        ActivityFragmentRouterSetupSyntax(expectThisToBeAFragmentActivity()).run {
            setup(savedInstanceState, containerId, fragmentManager)
        }
    }
}
