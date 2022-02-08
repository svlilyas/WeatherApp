package com.mobilion.data.util

import com.mobilion.data.Resource
import io.reactivex.rxjava3.core.ObservableEmitter
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import timber.log.Timber

fun <T> Single<T>.toObservable(emitter: ObservableEmitter<Resource<T>>): Disposable {
    emitter.onNext(
        Resource.loading()
    )
    return this
        .subscribeOn(Schedulers.io())
        .subscribe(
            {
                emitter.onNext(
                    Resource.success(it)
                )
                emitter.onComplete()
            },
            {
                emitter.onNext(
                    Resource.error(error = it)
                )

                emitter.onComplete()
            }
        )
}
