package com.mobilion.weatherapproute.android

import android.os.Parcelable
import com.mobilion.weatherapp.routecore.Route
import com.mobilion.weatherapp.routecore.RoutingStack
import kotlinx.android.parcel.Parcelize

/**
 * Wraps all elements using the `Element.parcelable` function.
 */
fun <T> Iterable<RoutingStack.Element<T>>.parcelable() where T : Route, T : Parcelable =
    this.map { entry -> entry.parcelable() }

/**
 * [RoutingStack.Element] implementation that also implements [Parcelable]
 */
@Parcelize
data class ParcelableElement<T>(
    override val key: ParcelableKey,
    override val route: T
) : RoutingStack.Element<T>(), Parcelable where T : Route, T : Parcelable
