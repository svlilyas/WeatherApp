package com.mobilion.weatherapp.features.customer.domain.viewevent

sealed class CustomerDashboardViewEvent {
    object GenerateQrCode : CustomerDashboardViewEvent()
}
