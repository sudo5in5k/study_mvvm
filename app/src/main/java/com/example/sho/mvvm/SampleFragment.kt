package com.example.sho.mvvm

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * TODO クラス説明
 *
 * Created by sho on 2018/11/01.
 */
class SampleFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("ushi_debug", "sampleFrag onCreate")
        retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        when (view?.tag) {
            "land" -> {
                Log.d("ushi_debug", "call land")
            }
            else -> {
                Log.d("ushi_debug", "call port")
            }
        }

        return inflater.inflate(R.layout.fragment_sample, container, false)
    }
}