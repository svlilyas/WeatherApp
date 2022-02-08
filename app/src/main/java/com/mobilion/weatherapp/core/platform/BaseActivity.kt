package com.mobilion.weatherapp.core.platform

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResult
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.FirebaseApp
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import com.mobilion.weatherapproute.android.fragment.KompassFragmentActivity
import io.reactivex.rxjava3.disposables.CompositeDisposable

abstract class BaseActivity<DB : ViewDataBinding, VM : BaseViewModel>(
    @LayoutRes private val layoutId: Int,
    private val viewModelClass: Class<VM>
) : AppCompatActivity(), KompassFragmentActivity {

    internal val disposable: CompositeDisposable by lazy { CompositeDisposable() }

    val binding by lazy {
        DataBindingUtil.setContentView(this, layoutId) as DB
    }
    val viewModel by lazy {
        ViewModelProvider(this).get(viewModelClass)
    }

    abstract fun onDataBinding()

    protected val activityLauncher: BetterActivityResult<Intent, ActivityResult> =
        BetterActivityResult.registerActivityForResult(this)

    lateinit var firebaseAnalytics: FirebaseAnalytics

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.lifecycleOwner = this

        //FirebaseApp.initializeApp(this)
        firebaseAnalytics = Firebase.analytics

        firebaseAnalytics.logEvent(
            FirebaseAnalytics.Event.SELECT_ITEM,
            bundleOf(Pair("sadsad", "sadsad"))
        )

        onDataBinding()
    }

    fun logEventFirebase(bundle: Bundle, eventName: String) {
        firebaseAnalytics.logEvent(eventName, bundle)
    }

    open fun finishApp() {
        finish()
        val finishIntent =
            Intent(Intent.ACTION_MAIN).apply {
                addCategory(Intent.CATEGORY_HOME)
                flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            }

        startActivity(finishIntent)
    }
}
