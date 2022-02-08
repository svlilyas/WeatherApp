package com.mobilion.weatherapp.features.customer.domain.usecase

import androidx.lifecycle.MutableLiveData
import com.mobilion.data.remote.response.Product
import com.mobilion.data.repository.ApiRepository
import javax.inject.Inject

class CustomerDashboardUseCase @Inject constructor(
    private val apiRepository: ApiRepository
) {
    fun addProduct(
        product: Product
    ) = apiRepository.addProduct(product)

    fun listenForQrRead(
        productId: String,
        liveData: MutableLiveData<Product>
    ) = apiRepository.listenForQrRead(productId = productId, liveData)
}
