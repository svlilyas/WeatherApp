package com.mobilion.weatherapproute.android.fragment

import android.graphics.Color
import androidx.fragment.app.Fragment

class EmptyFragment : Fragment() {
    init {
        this.view?.isClickable = false
        this.view?.setBackgroundColor(Color.WHITE)
    }
    companion object {
        val instance by lazy { EmptyFragment() }
    }
}
