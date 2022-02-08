package com.mobilion.weatherapp.features.cashier.domain.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mobilion.data.remote.response.Product
import com.mobilion.weatherapp.core.common.PreferenceManager
import com.mobilion.weatherapp.core.common.data.PaymentStatus
import com.mobilion.weatherapp.core.extensions.Event
import com.mobilion.weatherapp.core.platform.BaseViewModel
import com.mobilion.weatherapp.features.cashier.domain.usecase.CashierDashboardUseCase
import com.mobilion.weatherapp.features.cashier.domain.viewevent.CashierDashboardViewEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CashierDashboardViewModel @Inject constructor(
    private val useCase: CashierDashboardUseCase,
    val preferenceManager: PreferenceManager
) : BaseViewModel() {

    private val _event = MutableLiveData<Event<CashierDashboardViewEvent>>()
    val event: LiveData<Event<CashierDashboardViewEvent>>
        get() = _event

    private val _products = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>>
        get() = _products

    private val _qrResultProduct = MutableLiveData<Product>()
    val qrResultProduct: LiveData<Product>
        get() = _qrResultProduct

    private val _cameraPermissionGranted = MutableLiveData<Boolean>()
    val cameraPermissionGranted: LiveData<Boolean>
        get() = _cameraPermissionGranted

    private fun sendEvent(event: CashierDashboardViewEvent) {
        _event.postValue(Event(event))
    }

    fun fetchProducts() = useCase.fetchProducts(_products)

    fun findProduct(productId: String) = useCase.findProduct(productId, _qrResultProduct)

    fun updateProduct(paymentStatus: PaymentStatus) {
        val product = _qrResultProduct.value
        product?.paymentStatus = paymentStatus.name
        useCase.updateProduct(product = product!!)
    }
}
