package com.mobilion.weatherapproute.android.fragment.internal

import com.mobilion.weatherapp.routecore.Route
import com.mobilion.weatherapproute.android.fragment.FragmentMap
import com.mobilion.weatherapproute.android.fragment.FragmentRouteStorageSyntax
import com.mobilion.weatherapproute.android.fragment.FragmentRoutingStackBundleSyntax

internal interface FragmentRouterConfiguration<T : Route> {
    val fragmentMap: FragmentMap<T>
    val fragmentRouteStorageSyntax: FragmentRouteStorageSyntax<T>
    val fragmentRoutingStackBundleSyntax: FragmentRoutingStackBundleSyntax<T>
}
