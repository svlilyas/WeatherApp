package com.mobilion.weatherapp.features.customer.presentation.route

import com.mobilion.weatherapp.core.router.AppRoute
import kotlinx.android.parcel.Parcelize

sealed class CustomerDashboardRouter : AppRoute() {
    @Parcelize
    object CustomerDashboard : CustomerDashboardRouter()
}
