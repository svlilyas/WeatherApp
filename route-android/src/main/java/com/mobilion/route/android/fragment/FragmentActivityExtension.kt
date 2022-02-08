package com.mobilion.weatherapproute.android.fragment

import androidx.fragment.app.FragmentActivity
import com.mobilion.weatherapp.routecore.exception.KompassException

/* Keep 87 */
interface FragmentActivityExtension

internal fun FragmentActivityExtension.expectThisToBeAFragmentActivity() =
    this as? FragmentActivity ?: throw KompassException(
        "${KompassFragmentActivity::class.java.simpleName} only works for Fragments"
    )
