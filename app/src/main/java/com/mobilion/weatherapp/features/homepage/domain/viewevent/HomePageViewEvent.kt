package com.mobilion.weatherapp.features.homepage.domain.viewevent

sealed class HomePageViewEvent {
    object NavigateCustomerPage : HomePageViewEvent()
    object NavigateCashierPage : HomePageViewEvent()
}
