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
import com.example.sho.mvvm.rxkotlin.retrofit.QiitaItemResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_qiita_list.*

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

    private fun subscribe(adapter: ArrayAdapter<String>): Disposable {
        return QiitaClient.client().items()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ t: Array<QiitaItemResponse>? ->
                    t?.forEach {
                        adapter.add(it.title)
                    }
                }, { e ->
                    Log.d("ushi_debug_error", e.message)
                })
    }
}