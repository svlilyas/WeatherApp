package com.mobilion.weatherapp.features

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.core.os.bundleOf
import com.dengage.sdk.models.Message
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import com.mobilion.weatherapp.R
import com.mobilion.weatherapp.core.common.PageType
import com.mobilion.weatherapp.core.platform.BaseActivity
import com.mobilion.weatherapp.core.router.DependenciesHandler.appRoute
import com.mobilion.weatherapp.databinding.ActivityMainBinding
import com.mobilion.weatherapp.features.homepage.presentation.route.HomePageRouter
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(
    layoutId = R.layout.activity_main,
    viewModelClass = MainViewModel::class.java
) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        appRoute.setup(savedInstanceState, R.id.frameLayout, supportFragmentManager)
    }

    /*   override fun notificationReceived(data: Message?) {
           Timber.e(data?.message)
       }

       override fun showNotificationMessage(data: Message?) {
           Timber.e(data?.message)
       }
   */
    override fun onDataBinding() {
        viewModel.setRouter(HomePageRouter.HomePage)
    }

    companion object {
        fun newIntent(context: Context, type: PageType, targetUrl: String = ""): Intent {
            return Intent(context, MainActivity::class.java).apply {
                putExtra("type", type.type)
                putExtra("targetUrl", targetUrl)
            }
        }
    }
}
