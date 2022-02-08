package com.mobilion.weatherapp.routecore.internal

class Lock {
    operator fun <T> invoke(action: () -> T): T {
        return action.invoke()
    }
}
