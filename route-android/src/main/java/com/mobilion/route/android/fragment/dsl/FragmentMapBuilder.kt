package com.mobilion.weatherapproute.android.fragment.dsl

import androidx.fragment.app.Fragment
import com.mobilion.weatherapp.routecore.Route
import com.mobilion.weatherapproute.android.fragment.EmptyFragmentMap
import com.mobilion.weatherapproute.android.fragment.FragmentMap
import com.mobilion.weatherapproute.android.fragment.plus
import kotlin.reflect.KClass

@FragmentRouterDsl
class FragmentMapBuilder<T : Route> {

    private var fragmentMap: FragmentMap<T> = EmptyFragmentMap()

    @FragmentRouterDsl
    inline fun <reified R : T> route(noinline mapping: R.() -> KClass<out Fragment>?) {
        add(LambdaFragmentMap(R::class, mapping))
    }

    @FragmentRouterDsl
    fun add(fragmentMap: FragmentMap<T>) {
         this.fragmentMap += fragmentMap
    }

    @FragmentRouterDsl
    operator fun FragmentMap<T>.unaryPlus() {
        add(this)
    }

    internal fun build(): FragmentMap<T> {
        return fragmentMap
    }
}
