package com.mobilion.weatherapproute.android.fragment

import android.os.Bundle
import com.mobilion.weatherapp.routecore.Route
import com.mobilion.weatherapp.routecore.RoutingStack

interface FragmentRoutingStackBundleSyntax<T : Route> {
    fun RoutingStack<T>.saveTo(outState: Bundle)
    fun Bundle.restore(): RoutingStack<T>?
}
