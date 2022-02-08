package com.mobilion.weatherapp.routecore.internal

import com.mobilion.weatherapp.routecore.Key
import com.mobilion.weatherapp.routecore.Route
import com.mobilion.weatherapp.routecore.RoutingStack

internal data class ElementImpl<T : Route>(override val route: T, override val key: Key = Key()) :
    RoutingStack.Element<T>()