package com.mobilion.weatherapp.core.router

import android.os.Parcelable
import com.mobilion.weatherapp.routecore.Route
import kotlinx.android.parcel.Parcelize

abstract class AppRoute : Route, Parcelable

@Parcelize
object MainRouter : AppRoute()
