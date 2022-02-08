package com.mobilion.weatherapproute.android.fragment

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.mobilion.weatherapp.routecore.Route

/**
 * Generic version of [FragmentTransition].
 * Allows for constraining the transition by the generic type parameters.
 * @see FragmentTransition
 */
interface GenericFragmentTransition<
    in ExitFragment : Fragment, in ExitRoute : Route,
    in EnterFragment : Fragment, in EnterRoute : Route> {
    fun setup(
        transaction: FragmentTransaction,
        exitFragment: ExitFragment,
        exitRoute: ExitRoute,
        enterFragment: EnterFragment,
        enterRoute: EnterRoute
    )
}
