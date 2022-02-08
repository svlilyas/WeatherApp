package com.mobilion.weatherapproute.android.fragment.dsl

import androidx.fragment.app.Fragment
import com.mobilion.weatherapp.routecore.Route
import com.mobilion.weatherapproute.android.fragment.FragmentMap
import kotlin.reflect.KClass

@PublishedApi
internal class LambdaFragmentMap<T : Route, R : T>(
    private val type: KClass<R>,
    private val lambda: R.() -> KClass<out Fragment>?
) : FragmentMap<T> {
    override fun get(route: T): KClass<out Fragment>? {
        return if (type.java.isInstance(route)) {
            @Suppress("UNCHECKED_CAST")
            lambda(route as R)
        } else null
    }
}
