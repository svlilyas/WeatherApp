package com.mobilion.weatherapp.features.homepage.presentation.route

import com.mobilion.weatherapp.core.router.AppRoute
import kotlinx.android.parcel.Parcelize

sealed class HomePageRouter : AppRoute() {
    @Parcelize
    object HomePage : HomePageRouter()
}
