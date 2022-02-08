package com.mobilion.weatherapp.core.extensions

import com.google.gson.Gson
import okhttp3.ResponseBody

/**
 * Parses [okhttp3.ResponseBody] to given [T] data class.
 */
inline fun <reified T> parseResponseBody(responseBody: ResponseBody): T =
    Gson().fromJson(
        responseBody.charStream(),
        T::class.java
    )
