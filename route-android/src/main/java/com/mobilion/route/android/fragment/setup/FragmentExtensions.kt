package com.mobilion.weatherapproute.android.fragment.setup

import androidx.fragment.app.Fragment
import com.mobilion.weatherapp.routecore.exception.KompassException
import com.mobilion.weatherapproute.android.fragment.KompassFragment

interface FragmentExtensions

/* KEEP-87 */
internal fun FragmentExtensions.expectThisToBeAFragment() = this as? Fragment ?: throw KompassException(
    "${KompassFragment::class.java.simpleName} only works for Fragments"
)
