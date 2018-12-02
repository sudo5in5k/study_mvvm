package com.example.sho.mvvm.rxkotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.example.sho.mvvm.R
import com.example.sho.mvvm.rxkotlin.retrofit.QiitaClient
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class QiitaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qiita)
    }

    override fun onResume() {
        super.onResume()
        subscribe()
    }

    private fun subscribe(): Subscription? {
        return QiitaClient.client().items().subscribeOn(Schedulers.newThread())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ item ->
                    item.forEach {
                        Log.d("ushi_debug", it.title)
                    }
                }, { e ->
                    Log.e("ushi_debug_error", e.message)
                })
    }

    override fun onPause() {
        super.onPause()
        subscribe()?.unsubscribe()
        Log.d("ushi_debug", "unsubscribe")
    }
}