package com.mobilion.weatherapproute.android.fragment

import androidx.fragment.app.Fragment
import com.mobilion.weatherapp.routecore.Route
import kotlin.reflect.KClass

interface FragmentRoute : Route {
    val fragment: KClass<out Fragment>
}
