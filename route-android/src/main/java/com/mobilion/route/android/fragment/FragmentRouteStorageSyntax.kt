package com.mobilion.weatherapproute.android.fragment

import androidx.fragment.app.Fragment
import com.mobilion.weatherapp.routecore.Route

/**
 * # FragmentRouteStorageSyntax
 * Defines a way of attaching a route to a fragment in a way, that this fragment can retrieve this route later.
 *
 * ## Usage
 * ```
 * fragmentRouteStorageSyntax.run { fragment.attach(route) } 
 * val route = fragmentRouteStorageSyntax.run {  fragment.getRouteOrNull() } 
 * ```
 *
 * @see FragmentGetRouteSyntax
 */
interface FragmentRouteStorageSyntax<T : Route> {
    fun Fragment.attach(route: T)
    fun Fragment.getRouteOrNull(): T?
    fun Fragment.getRoute(): T
}
