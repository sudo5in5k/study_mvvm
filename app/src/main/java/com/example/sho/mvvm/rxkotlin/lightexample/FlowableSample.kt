package com.example.sho.mvvm.rxkotlin.lightexample

import android.util.Log
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.FlowableOnSubscribe
import io.reactivex.schedulers.Schedulers
import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription


object FlowableSample {

    // Do not use rx.Observable
    fun create() {
        val data = listOf("Hello", "world", "こんにちは")
        val flowable = Flowable.create(FlowableOnSubscribe<String> { e ->
            data.forEach {
                if (e.isCancelled) {
                    return@forEach
                }
                e.onNext(it)
            }
            e.onComplete()
        }, BackpressureStrategy.BUFFER)

        flowable.observeOn(Schedulers.computation())
                .subscribe(object : Subscriber<String> {

                    private var subscription: Subscription? = null

                    override fun onComplete() {
                        val threadName = Thread.currentThread().name
                        Log.d("ushi_debug", "$threadName 完了")
                    }

                    override fun onSubscribe(s: Subscription?) {
                        subscription = s
                        subscription?.request(1L)
                    }

                    override fun onNext(t: String?) {
                        if (t == null) {
                            return
                        }
                        val threadName = Thread.currentThread().name
                        Log.d("ushi_debug", "$t を出力しました")
                        subscription?.request(1L)
                    }

                    override fun onError(t: Throwable) {
                        Log.d("ushi_debug", "Error!!! ${t.message}")
                    }

                })
    }
}