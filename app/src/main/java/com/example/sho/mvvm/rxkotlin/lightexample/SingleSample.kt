package com.example.sho.mvvm.rxkotlin.lightexample

import android.annotation.SuppressLint
import android.os.Build
import android.support.annotation.RequiresApi
import android.util.Log
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.time.DayOfWeek
import java.time.LocalDate

object SingleSample {

    @SuppressLint("CheckResult")
    @RequiresApi(Build.VERSION_CODES.O)
    fun create() {

        Single.fromCallable { LocalDate.now().dayOfWeek }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                //.subscribe { result -> Log.d("ushi_debug", result.toString())}
                .subscribe(object : SingleObserver<DayOfWeek> {
                    override fun onSuccess(t: DayOfWeek) {
                        Log.d("ushi_debug", t.toString())
                    }

                    override fun onSubscribe(d: Disposable) {
                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    }

                    override fun onError(e: Throwable) {
                        Log.d("ushi_debug", e.message)
                    }

                })
    }
}