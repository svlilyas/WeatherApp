package com.mobilion.weatherapproute.android.fragment.dsl

import androidx.fragment.app.Fragment
import com.mobilion.weatherapp.routecore.Route
import com.mobilion.weatherapproute.android.fragment.FragmentTransition
import com.mobilion.weatherapproute.android.fragment.GenericFragmentTransition
import com.mobilion.weatherapproute.android.fragment.internal.EmptyFragmentTransition
import com.mobilion.weatherapproute.android.fragment.internal.erased
import com.mobilion.weatherapproute.android.fragment.internal.reified
import com.mobilion.weatherapproute.android.fragment.plus

@FragmentRouterDsl
class FragmentTransitionBuilder {

    private var transition: FragmentTransition = EmptyFragmentTransition

    @FragmentRouterDsl
    fun register(transition: FragmentTransition) {
        this.transition += transition
    }

    @JvmName("registerGeneric")
    @FragmentRouterDsl
    inline fun <reified ExitFragment : Fragment, reified ExitRoute : Route,
        reified EnterFragment : Fragment, reified EnterRoute : Route> register(
        transition: GenericFragmentTransition<ExitFragment, ExitRoute, EnterFragment, EnterRoute>
    ) = register(transition.reified().erased())

    internal fun build(): FragmentTransition {
        return transition
    }
}
