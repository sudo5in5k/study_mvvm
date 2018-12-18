package com.example.sho.mvvm.rxkotlin.lightexample

import android.util.Log
import io.reactivex.Flowable
import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription
import java.util.concurrent.TimeUnit

object FlowableCanCancelSample {

    fun create() {

        Flowable.interval(200L, TimeUnit.MILLISECONDS)
                .subscribe(object : Subscriber<Long> {

                    private lateinit var subscription: Subscription
                    private var startTime: Long = 0L

                    override fun onComplete() {
                        Log.d("ushi_debug", "完了")
                    }

                    override fun onSubscribe(s: Subscription?) {
                        if (s == null) {
                            return
                        }
                        subscription = s
                        startTime = System.currentTimeMillis()
                        subscription.request(Long.MAX_VALUE)
                    }

                    override fun onNext(t: Long?) {
                        if (System.currentTimeMillis() - startTime > 500L) {
                            subscription.cancel()
                            Log.d("ushi_debug", "End Subscription")
                            return
                        }
                        Log.d("ushi_debug", "$t を出力しました")
                    }

                    override fun onError(t: Throwable?) {
                        Log.d("ushi_debug", "Error!!! ${t?.message}")
                    }

                })
    }
}