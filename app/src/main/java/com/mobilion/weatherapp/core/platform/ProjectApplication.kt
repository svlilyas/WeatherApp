package com.mobilion.weatherapp.core.platform

import android.app.Application
import android.content.Context
import androidx.core.os.bundleOf
import androidx.lifecycle.MutableLiveData
import androidx.multidex.MultiDex
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.FirebaseApp
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.FirebaseMessaging
import com.mobilion.weatherapp.core.common.PreferenceManager
import com.mobilion.weatherapp.core.network.NetworkUnavailableException
import com.mobilion.weatherapp.core.router.DependenciesHandler
import com.mobilion.weatherapp.core.router.RouteMapInjection.getMapRoutes
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class ProjectApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext

        injectMultiDex()
        startRoutes()

        if (com.mobilion.weatherapp.BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        //FirebaseApp.initializeApp(this)
        //firebaseAnalytics.setAnalyticsCollectionEnabled(true)
        FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(true)

        FirebaseMessaging.getInstance().isAutoInitEnabled = true
        FirebaseMessaging.getInstance().token.addOnCompleteListener(
            OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Timber.e("Fetching FCM registration token failed ${task.exception}")
                    return@OnCompleteListener
                }

                // Get new FCM registration token
                val token = task.result

                Timber.e(token)
            })
        //FirebaseService.createChannelAndHandleNotifications(this)
    }

    private fun startRoutes() {
        DependenciesHandler.appRoute = getMapRoutes()
    }

    private fun injectMultiDex() {
        MultiDex.install(this.applicationContext)
    }

    companion object {
        lateinit var appContext: Context

        /**
         * Used for checking internet connectivity. Main usage is on [ProjectApiRequestInterceptor]
         */
        /* val dengageEvent by lazy {
             DengageEvent.getInstance(appContext)
         }*/
        val networkStatusObservable: MutableLiveData<NetworkUnavailableException> by lazy {
            MutableLiveData()
        }
        val preferenceManager: PreferenceManager by lazy {
            PreferenceManager(appContext)
        }
    }
}
