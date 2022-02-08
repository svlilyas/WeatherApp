package com.mobilion.weatherapp.features.cashier.domain.viewevent

sealed class CashierDashboardViewEvent {
    object ReadQrCode : CashierDashboardViewEvent()
}
