package com.mobilion.weatherapp.features.cashier.presentation.route

import com.mobilion.weatherapp.core.router.AppRoute
import kotlinx.android.parcel.Parcelize

sealed class CashierDashboardRouter : AppRoute() {
    @Parcelize
    object CashierDashboard : CashierDashboardRouter()
}
