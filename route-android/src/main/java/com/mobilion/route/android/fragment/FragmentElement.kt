package com.mobilion.weatherapproute.android.fragment

import androidx.fragment.app.Fragment
import com.mobilion.weatherapp.routecore.Route
import com.mobilion.weatherapp.routecore.RoutingStack

abstract class FragmentElement<T : Route> : RoutingStack.Element<T>() {

    interface Factory<T : Route> {
        operator fun invoke(element: RoutingStack.Element<T>): FragmentElement<T>
    }

    abstract fun createFragment(): Fragment
}
