package com.mobilion.weatherapp.core.router

import com.mobilion.weatherapp.features.cashier.presentation.CashierDashboardFragment
import com.mobilion.weatherapp.features.cashier.presentation.route.CashierDashboardRouter
import com.mobilion.weatherapp.features.customer.presentation.CustomerDashboardFragment
import com.mobilion.weatherapp.features.customer.presentation.route.CustomerDashboardRouter
import com.mobilion.weatherapp.features.homepage.presentation.HomePageFragment
import com.mobilion.weatherapp.features.homepage.presentation.route.HomePageRouter
import com.mobilion.weatherapproute.android.fragment.FragmentRouter

object RouteMapInjection {
    fun getMapRoutes(): FragmentRouter<AppRoute> = FragmentRouter {
        routing {
            /**Base Router Mapping*/
            route<HomePageRouter> { HomePageFragment::class }
            route<CustomerDashboardRouter> { CustomerDashboardFragment::class }
            route<CashierDashboardRouter> { CashierDashboardFragment::class }
        }
    }
}