package com.example.sho.mvvm.rxkotlin.lightexample

import android.content.Context
import android.util.Log
import android.widget.Toast
import io.reactivex.Completable
import io.reactivex.CompletableObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable

object CompletableSample {

    fun create(context: Context) {
        Completable.fromAction { Toast.makeText(context, "Completable sample", Toast.LENGTH_LONG).show() }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : CompletableObserver {
                    override fun onComplete() {
                        Log.d("ushi_debug", "complete!")
                    }

                    override fun onSubscribe(d: Disposable) {
                        Log.d("ushi_debug", "ready!")
                    }

                    override fun onError(e: Throwable) {
                        Log.d("ushi_debug", e.message)
                    }

                })
    }
}