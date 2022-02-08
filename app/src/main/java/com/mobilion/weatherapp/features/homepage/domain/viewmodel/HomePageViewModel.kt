package com.mobilion.weatherapp.features.homepage.domain.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mobilion.data.Status
import com.mobilion.weatherapp.core.common.PreferenceManager
import com.mobilion.weatherapp.core.extensions.Event
import com.mobilion.weatherapp.core.platform.BaseViewModel
import com.mobilion.weatherapp.features.homepage.domain.usecase.HomePageUseCase
import com.mobilion.weatherapp.features.homepage.domain.viewevent.HomePageViewEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomePageViewModel @Inject constructor(
    private val useCase: HomePageUseCase,
    val preferenceManager: PreferenceManager
) : BaseViewModel() {
    private val _event = MutableLiveData<Event<HomePageViewEvent>>()
    val event: LiveData<Event<HomePageViewEvent>>
        get() = _event

    private fun sendEvent(event: HomePageViewEvent) {
        _event.postValue(Event(event))
    }

    fun navigateToCustomerPage() {
        sendEvent(HomePageViewEvent.NavigateCustomerPage)
    }

    fun navigateToCashierPage() {
        sendEvent(HomePageViewEvent.NavigateCashierPage)
    }
}
