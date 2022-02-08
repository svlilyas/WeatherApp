package com.mobilion.weatherapp.core.platform

import android.os.Bundle
import android.util.Base64
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.adjust.sdk.Adjust
import com.adjust.sdk.AdjustEvent
import com.google.firebase.analytics.FirebaseAnalytics
import com.mobilion.weatherapp.core.extensions.Event
import com.mobilion.weatherapp.core.platform.ProjectApplication.Companion.appContext
import com.mobilion.weatherapp.core.router.AppRoute
import com.mobilion.weatherapp.core.router.DependenciesHandler
import com.mobilion.weatherapp.routecore.push
import com.mobilion.weatherapproute.android.ParcelableElement
import com.mobilion.weatherapproute.android.ParcelableKey
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import retrofit2.HttpException
import timber.log.Timber
import java.util.*
import kotlin.collections.ArrayList

abstract class BaseViewModel : ViewModel() {

    /**
     * [LiveData] that emits [ProgressState] to determine show/hide state of loading indicators(ie: HUD)
     */
    internal val progressStateObservable: MutableLiveData<ProgressState> by lazy {
        MutableLiveData()
    }
    val stackObservable: MutableLiveData<ArrayList<AppRoute>> by lazy {
        MutableLiveData()
    }
    internal val firebaseAnalytics: FirebaseAnalytics by lazy {
        FirebaseAnalytics.getInstance(appContext)
    }

    internal var disposable = CompositeDisposable()

    val stackList = ArrayList<AppRoute>()

    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    private val _baseEvent = MutableLiveData<Event<BaseViewEvent>>()
    val baseEvent: LiveData<Event<BaseViewEvent>> = _baseEvent

    private val _isResponseEmpty = MutableLiveData(false)
    val isResponseEmpty: LiveData<Boolean> = _isResponseEmpty

    fun setLoading(loading: Boolean) = _loading.postValue(loading)

    fun setEmpty(isResponseEmpty: Boolean) = _isResponseEmpty.postValue(isResponseEmpty)

    fun setStack(routeName: AppRoute) {
        stackList.add(routeName)
        stackObservable.postValue(stackList)
    }

    /**
     * Disposes un-disposed subscriptions, should be called at onStop/onDestroy lifecycle state
     */

    internal fun disposeSubscriptions() {
        if (!disposable.isDisposed) disposable.dispose()
    }

    internal fun clearSubscriptions() {
        disposable.clear()
    }

    internal fun emitProgressShow() {
        progressStateObservable.postValue(ProgressState.Show)
    }

    operator fun CompositeDisposable.plusAssign(disposable: Disposable) {
        add(disposable)
    }

    override fun onCleared() {
        disposeSubscriptions()
        super.onCleared()
    }

    fun encodeBase64(string: String): String {
        return Base64.encodeToString(
            string.toByteArray(),
            Base64.NO_WRAP
        )
    }

    fun logEventFirebase(eventName: String, bundle: Bundle) {
        firebaseAnalytics.logEvent(eventName, bundle)
    }

    fun logEvenAdjust(event: AdjustEvent) = Adjust.trackEvent(event)

    internal fun setRouter(routeName: AppRoute, keyName: String? = null) {
        setStack(routeName)
        val router = ParcelableElement(
            key = ParcelableKey(UUID.randomUUID().toString()),
            routeName
        )

        DependenciesHandler.appRoute {
            push(router)
        }
    }

    internal fun setSubRouter(routeName: AppRoute, keyName: String? = null) {
        setStack(routeName)
        val router = ParcelableElement(
            key = ParcelableKey(UUID.randomUUID().toString()),
            routeName
        )

        DependenciesHandler.subRouter {
            push(router)
        }
    }

    open fun handleException(e: Throwable?) {
        Timber.e(e)
        when (e) {
            is HttpException -> {
                when (e.code()) {
                    /*401, 462 -> forceLogout()

                    403 -> _baseEvent.postValue(Event(BaseViewEvent.ShowUserNotFoundError))*/
                    500 -> _baseEvent.postValue(Event(BaseViewEvent.ShowInternalServerError))
                    //else -> showCommonNetworkError()
                    /*else -> {
                        if (e.code() in 499..599) {
                            _baseEvent.postValue(Event(BaseViewEvent.ShowInternalServerError))
                        } else {
                            try {
                                Gson().fromJson(
                                    e.response()?.errorBody()?.string(),
                                    ErrorResponse::class.java
                                )?.errorMessage?.let {
                                    showCustomError(
                                        it
                                    )
                                }
                            } catch (exception: Exception) {
                                showCommonNetworkError()
                            }
                        }
                    }*/
                }
            }

            /*is JsonSyntaxException -> showCommonNetworkError()

            is UnknownHostException -> showCommonNetworkError()
            else -> showCommonNetworkError()*/
        }
    }

    /**
     * Used with [progressStateObservable] for emitting state to show/hide loading indicators.(ie: HUD)
     */
    sealed class ProgressState {
        object Show : ProgressState()
        object Hide : ProgressState()
    }
}
