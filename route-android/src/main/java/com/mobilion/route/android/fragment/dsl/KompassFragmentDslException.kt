package com.mobilion.weatherapproute.android.fragment.dsl

import com.mobilion.weatherapp.routecore.exception.KompassException

class KompassFragmentDslException : KompassException {
    constructor() : super()
    constructor(message: String?) : super(message)
    constructor(message: String?, cause: Throwable?) : super(message, cause)
    constructor(cause: Throwable?) : super(cause)
}
