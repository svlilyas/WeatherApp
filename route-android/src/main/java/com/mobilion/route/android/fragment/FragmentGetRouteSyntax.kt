package com.mobilion.weatherapproute.android.fragment

import com.mobilion.weatherapp.routecore.Route
import com.mobilion.weatherapproute.android.GetRouteSyntax
import com.mobilion.weatherapproute.android.fragment.setup.FragmentExtensions
import com.mobilion.weatherapproute.android.fragment.setup.expectThisToBeAFragment
import kotlin.reflect.KClass

interface FragmentGetRouteSyntax :
    GetRouteSyntax,
    FragmentExtensions {

    override val router: FragmentRouter<*>

    override fun <R : Route> getRouteOrNull(clazz: KClass<R>): R? {
        val route =
            router.fragmentRouteStorageSyntax.run { expectThisToBeAFragment().getRouteOrNull() }
        if (clazz.java.isInstance(route)) {
            @Suppress("UNCHECKED_CAST")
            return route as? R
        }

        return null
    }
}
