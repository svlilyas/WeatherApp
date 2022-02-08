package com.mobilion.weatherapproute.android.fragment

import androidx.fragment.app.Fragment
import com.mobilion.weatherapp.routecore.Route
import kotlin.reflect.KClass

class EmptyFragmentMap<T : Route> : FragmentMap<T> {
    override fun get(route: T): KClass<out Fragment>? = null
}
