package com.mobilion.weatherapproute.android.fragment

import androidx.fragment.app.Fragment
import com.mobilion.weatherapp.routecore.Route
import kotlin.reflect.KClass

operator fun <T : Route> FragmentMap<T>.plus(other: FragmentMap<T>): FragmentMap<T> {
    return CompositeFragmentMap(this, other)
}

private class CompositeFragmentMap<T : Route>(
    private val first: FragmentMap<T>,
    private val second: FragmentMap<T>
) : FragmentMap<T> {
    override fun get(route: T): KClass<out Fragment>? {
        return first[route] ?: second[route]
    }
}
