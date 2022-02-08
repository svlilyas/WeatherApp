package com.mobilion.weatherapp.features.homepage.domain.usecase

import com.mobilion.data.repository.ApiRepository
import javax.inject.Inject

class HomePageUseCase @Inject constructor(
    private val apiRepository: ApiRepository
) {
}
