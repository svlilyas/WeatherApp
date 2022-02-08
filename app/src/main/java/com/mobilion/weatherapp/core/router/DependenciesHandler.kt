package com.mobilion.weatherapp.core.router

import com.mobilion.weatherapproute.android.fragment.FragmentRouter
import kotlin.properties.Delegates

object DependenciesHandler {
    var appRoute: FragmentRouter<AppRoute> by Delegates.notNull()

    var subRouter: FragmentRouter<AppRoute> by Delegates.notNull()
}
