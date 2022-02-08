package com.mobilion.weatherapproute.android.fragment

import com.mobilion.weatherapp.routecore.Route
import com.mobilion.weatherapp.routecore.exception.KompassException

class FragmentMappingMissingException(route: Route) : KompassException(
    """
    Missing fragment mapping for route $route.
    Consider implementing `FragmentRoute`, specifying a `FragmentMap` or declaring it via DSL:
    FragmentRouter { 
        routing {
            route<${route::class.java.simpleName}> { [TODO] }
        }
    }
    """.trimIndent()
)
