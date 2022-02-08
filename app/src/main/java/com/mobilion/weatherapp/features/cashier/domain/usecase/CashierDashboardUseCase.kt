package com.mobilion.weatherapp.features.cashier.domain.usecase

import androidx.lifecycle.MutableLiveData
import com.mobilion.data.remote.response.Product
import com.mobilion.data.repository.ApiRepository
import javax.inject.Inject

class CashierDashboardUseCase @Inject constructor(
    private val apiRepository: ApiRepository
) {
    fun fetchProducts(
        liveData: MutableLiveData<List<Product>>
    ) = apiRepository.fetchProducts(liveData)

    fun findProduct(
        productId: String,
        liveData: MutableLiveData<Product>
    ) = apiRepository.findProduct(productId = productId, liveData)

    fun updateProduct(
        product: Product
    ) = apiRepository.updateProduct(product = product)
}
