package com.mobilion.weatherapproute.android.fragment

import androidx.annotation.IdRes
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager

class FragmentContainer(
    val activity: FragmentActivity,
    var fragmentManager: FragmentManager,
    @IdRes val id: Int
) 