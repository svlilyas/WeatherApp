package com.mobilion.weatherapproute.android.fragment

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.fragment.app.FragmentManager
import com.mobilion.weatherapproute.android.fragment.setup.FragmentExtensions
import com.mobilion.weatherapproute.android.fragment.setup.FragmentFragmentRouterSetupSyntax
import com.mobilion.weatherapproute.android.fragment.setup.expectThisToBeAFragment

interface KompassFragment :
    FragmentExtensions,
    FragmentGetRouteSyntax {

    override val router: FragmentRouter<*>

    fun FragmentRouter<*>.setup(
        savedInstanceState: Bundle?,
        @IdRes containerId: Int,
        fragmentManager: FragmentManager = expectThisToBeAFragment().childFragmentManager
    ) {
        FragmentFragmentRouterSetupSyntax(expectThisToBeAFragment()).run {
            setup(savedInstanceState, containerId, fragmentManager)
        }
    }
}
