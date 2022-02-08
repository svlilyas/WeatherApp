package com.mobilion.weatherapp.core.binding

import androidx.databinding.BindingAdapter
import com.jaygoo.widget.RangeSeekBar

@BindingAdapter("app:maxValue")
fun setProgressRange(seekbar: RangeSeekBar, max: Float) =
    seekbar.setRange(10000F, max, 5000f)
