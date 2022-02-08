package com.mobilion.weatherapproute.android.fragment

import   android.os.Bundle
import android.os.Parcelable
import com.mobilion.weatherapp.routecore.Route
import com.mobilion.weatherapp.routecore.RoutingStack
import com.mobilion.weatherapp.routecore.routes
import com.mobilion.weatherapproute.android.ParcelableRoutingStack
import com.mobilion.weatherapproute.android.parcelable
import com.mobilion.weatherapproute.android.utils.log

class ParcelableFragmentRoutingStackBundleSyntax<T>(
    private val key: String = defaultKey
) : FragmentRoutingStackBundleSyntax<T> where T : Route, T : Parcelable {

    override fun RoutingStack<T>.saveTo(outState: Bundle) {
        log("saving routes: ${this.routes.joinToString(", ")}")
        outState.putParcelable(key, parcelable())
    }

    override fun Bundle.restore(): RoutingStack<T>? {
        return getParcelable<ParcelableRoutingStack<T>>(key).also {
            log("restored routes: ${it?.routes?.joinToString(", ")}")
        }
    }

    companion object {
        const val defaultKey = "WeatherApp: RoutingStack"
    }
}
