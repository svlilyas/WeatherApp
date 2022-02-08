package com.mobilion.weatherapproute.android

import android.os.Parcelable
import com.mobilion.weatherapp.routecore.Route
import com.mobilion.weatherapp.routecore.RoutingStack

interface ParcelableRoutingStack<T : Route> : RoutingStack<T>, Parcelable
