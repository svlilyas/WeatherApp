package com.mobilion.weatherapproute.android.fragment.internal

import android.os.Parcelable
import com.mobilion.weatherapp.routecore.Route
import com.mobilion.weatherapproute.android.fragment.FragmentRouteStorageSyntax
import com.mobilion.weatherapproute.android.fragment.FragmentRoutingStackBundleSyntax
import com.mobilion.weatherapproute.android.fragment.ParcelableFragmentRouteStorageSyntax
import com.mobilion.weatherapproute.android.fragment.ParcelableFragmentRoutingStackBundleSyntax

internal fun <T : Route> ParcelableFragmentRouteStorageSyntax.Companion.createUnsafe(): FragmentRouteStorageSyntax<T> {
    @Suppress("UNCHECKED_CAST")
    return ParcelableFragmentRouteStorageSyntax<ParcelableRoute>() as FragmentRouteStorageSyntax<T>
}

internal fun <T : Route> ParcelableFragmentRoutingStackBundleSyntax.Companion.createUnsafe(
    key: String = defaultKey
): FragmentRoutingStackBundleSyntax<T> {
    @Suppress("UNCHECKED_CAST")
    return ParcelableFragmentRoutingStackBundleSyntax<ParcelableRoute>(key) as FragmentRoutingStackBundleSyntax<T>
}

/**
 * Just a private interface to trick the compiler :)
 */
private interface ParcelableRoute : Route, Parcelable
