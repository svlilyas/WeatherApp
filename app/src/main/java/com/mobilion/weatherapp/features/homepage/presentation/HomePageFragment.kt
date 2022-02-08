package com.mobilion.weatherapp.features.homepage.presentation

import android.os.Bundle
import com.mobilion.weatherapp.R
import com.mobilion.weatherapp.core.extensions.observeEvent
import com.mobilion.weatherapp.core.platform.BaseFragment
import com.mobilion.weatherapp.core.router.PageName
import com.mobilion.weatherapp.databinding.FragmentHomepageBinding
import com.mobilion.weatherapp.features.cashier.presentation.route.CashierDashboardRouter
import com.mobilion.weatherapp.features.customer.presentation.route.CustomerDashboardRouter
import com.mobilion.weatherapp.features.homepage.domain.viewevent.HomePageViewEvent
import com.mobilion.weatherapp.features.homepage.domain.viewmodel.HomePageViewModel
import com.mobilion.weatherapp.features.homepage.presentation.route.HomePageRouter
import com.mobilion.weatherapproute.android.route
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomePageFragment : BaseFragment<FragmentHomepageBinding, HomePageViewModel>(
    layoutId = R.layout.fragment_homepage,
    viewModelClass = HomePageViewModel::class.java
) {
    val routes: HomePageRouter.HomePage by route()
    override fun getScreenKey(): String = PageName.PreLogin.HOMEPAGE_MAIN

    override fun onDataBinding() {
        binding.apply {
            viewmodel = viewModel
        }
        observeEvent(viewModel.event, ::onViewEvent)
    }

    private fun onViewEvent(event: HomePageViewEvent) {
        when (event) {
            HomePageViewEvent.NavigateCashierPage -> {
                viewModel.setRouter(CashierDashboardRouter.CashierDashboard)
            }
            HomePageViewEvent.NavigateCustomerPage -> {
                viewModel.setRouter(CustomerDashboardRouter.CustomerDashboard)
            }
        }
    }

    companion object {
        fun newInstance(args: Bundle?): HomePageFragment {
            val fragment = HomePageFragment()
            fragment.arguments = args
            return fragment
        }
    }
}
