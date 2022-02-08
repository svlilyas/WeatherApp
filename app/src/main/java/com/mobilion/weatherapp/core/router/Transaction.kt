package com.mobilion.weatherapp.core.router

import android.annotation.SuppressLint
import android.transition.Slide
import android.view.Gravity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.mobilion.weatherapp.routecore.Route
import com.mobilion.weatherapproute.android.fragment.FragmentTransition

class Transaction : FragmentTransition {
    @SuppressLint("RtlHardcoded")
    override fun setup(
        transaction: FragmentTransaction,
        exitFragment: Fragment,
        exitRoute: Route,
        enterFragment: Fragment,
        enterRoute: Route
    ) {

        if (exitFragment.isVisible && !enterFragment.isVisible) {
            exitFragment.exitTransition = Slide(Gravity.RIGHT)
            enterFragment.enterTransition = Slide(Gravity.LEFT)
        } else {
            exitFragment.exitTransition = Slide(Gravity.LEFT)
            enterFragment.enterTransition = Slide(Gravity.RIGHT)
        }
    }
}
