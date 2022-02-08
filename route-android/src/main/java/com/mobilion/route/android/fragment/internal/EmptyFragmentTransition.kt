package com.mobilion.weatherapproute.android.fragment.internal

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.mobilion.weatherapp.routecore.Route
import  com.mobilion.weatherapproute.android.fragment.FragmentTransition

internal object EmptyFragmentTransition : FragmentTransition {
    override fun setup(
        transaction: FragmentTransaction,
        exitFragment: Fragment,
        exitRoute: Route,
        enterFragment: Fragment,
        enterRoute: Route
    ) = Unit
}
