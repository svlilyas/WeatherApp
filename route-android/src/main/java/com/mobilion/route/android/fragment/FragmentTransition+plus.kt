package com.mobilion.weatherapproute.android.fragment

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.mobilion.weatherapp.routecore.Route

operator fun FragmentTransition.plus(other: FragmentTransition): FragmentTransition {
    return CompositeFragmentTransition(this, other)
}

private class CompositeFragmentTransition(
    private val first: FragmentTransition,
    private val second: FragmentTransition
) : FragmentTransition {
    override fun setup(
        transaction: FragmentTransaction,
        exitFragment: Fragment,
        exitRoute: Route,
        enterFragment: Fragment,
        enterRoute: Route
    ) {
        first.setup(transaction, exitFragment, exitRoute, enterFragment, enterRoute)
        second.setup(transaction, exitFragment, exitRoute, enterFragment, enterRoute)
    }
}
