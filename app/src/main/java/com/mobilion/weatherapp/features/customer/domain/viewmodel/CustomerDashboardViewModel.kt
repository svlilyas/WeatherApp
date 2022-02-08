package com.mobilion.weatherapp.features.customer.domain.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mobilion.data.remote.response.Product
import com.mobilion.weatherapp.core.common.PreferenceManager
import com.mobilion.weatherapp.core.extensions.Event
import com.mobilion.weatherapp.core.platform.BaseViewModel
import com.mobilion.weatherapp.features.customer.domain.usecase.CustomerDashboardUseCase
import com.mobilion.weatherapp.features.customer.domain.viewevent.CustomerDashboardViewEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CustomerDashboardViewModel @Inject constructor(
    private val useCase: CustomerDashboardUseCase,
    val preferenceManager: PreferenceManager
) : BaseViewModel() {
    private val _event = MutableLiveData<Event<CustomerDashboardViewEvent>>()
    val event: LiveData<Event<CustomerDashboardViewEvent>>
        get() = _event

    private val _qrReadListener = MutableLiveData<Product>()
    val qrReadListener: LiveData<Product>
        get() = _qrReadListener

    private fun sendEvent(event: CustomerDashboardViewEvent) {
        _event.postValue(Event(event))
    }

    fun generateQrCode() {
        sendEvent(CustomerDashboardViewEvent.GenerateQrCode)
    }

    fun addProduct(product: Product) {
        _qrReadListener.postValue(product)
        useCase.addProduct(product = product)
    }

    fun listenForQrRead(productId: String) =
        useCase.listenForQrRead(productId = productId, liveData = _qrReadListener)
}
