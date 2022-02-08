package com.mobilion.weatherapp.core.common.deeplink

object InternalDeepLink {
    const val DOMAIN = "myapp://"

    const val MAIN = "${DOMAIN}main"

    fun makeCustomDeepLink(id: String): String {
        return "${DOMAIN}customDeepLink?id=$id"
    }
}
