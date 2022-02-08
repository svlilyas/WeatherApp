package com.mobilion.weatherapproute.android.fragment

import com.mobilion.weatherapp.routecore.RoutingStack

interface FragmentStackPatcher {
    operator fun invoke(
        transition: FragmentTransition,
        container: FragmentContainer,
        oldStack: RoutingStack<*>,
        newStack: FragmentRoutingStack<*>
    )
}
