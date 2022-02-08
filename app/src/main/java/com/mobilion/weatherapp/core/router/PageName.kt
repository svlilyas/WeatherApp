package com.mobilion.weatherapp.core.router

object PageName {
    const val marketAppMain = "https://weatherapp.com.tr"
    const val MAIN = "$marketAppMain/main"

    object PreLogin {
        const val HOMEPAGE_MAIN = "$marketAppMain/homePage"

        const val CUSTOMER_DASHBOARD = "$HOMEPAGE_MAIN/customer_dashboard"
        const val CASHIER_DASHBOARD = "$HOMEPAGE_MAIN/cashier_dashboard"
    }
}
