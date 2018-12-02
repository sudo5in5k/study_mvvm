package com.example.sho.mvvm.rxkotlin

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.sho.mvvm.R
import com.example.sho.mvvm.rxkotlin.retrofit.QiitaClient
import kotlinx.android.synthetic.main.fragment_qiita_list.*
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class QiitaListFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_qiita_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val adapter = ArrayAdapter<String>(context, android.R.layout.simple_list_item_activated_1)
        subscribe(adapter)
        qiita_list.adapter = adapter
    }

    private fun subscribe(adapter: ArrayAdapter<String>): Subscription? {
        return QiitaClient.client().items().subscribeOn(Schedulers.newThread())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ item ->
                    item.forEach {
                        adapter.add(it.title)
                    }
                }, { e ->
                    Log.e("ushi_debug_error", e.message)
                })
    }
}