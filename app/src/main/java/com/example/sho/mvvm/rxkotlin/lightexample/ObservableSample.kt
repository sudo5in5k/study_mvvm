package com.example.sho.mvvm.rxkotlin.lightexample

import android.util.Log
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

object ObservableSample {

    // 2系ではこちらをよく多用する
    fun create() {
        // 今回はラムダ表記
        val observable: Observable<String> = Observable.create { e ->
            val data = listOf("Hello", "Rx", "Kotlin", "2")
            data.forEach {
                if (e.isDisposed) {
                    return@create
                }
                e.onNext(it)
            }
            e.onComplete()
        }

        observable.observeOn(Schedulers.computation())
                .subscribe(object : Observer<String> {
                    override fun onComplete() {
                        Log.d("ushi_debug", Thread.currentThread().name)
                    }

                    override fun onSubscribe(d: Disposable) {
                        // nothing
                    }

                    override fun onNext(t: String) {
                        Log.d("ushi_debug", t)
                    }

                    override fun onError(e: Throwable) {
                        Log.d("ushi_debug", "$e")
                    }
                })
    }
}