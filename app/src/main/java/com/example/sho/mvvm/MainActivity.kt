package com.example.sho.mvvm

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.FragmentTransaction
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var count: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("ushi_debug", "onCreate")

        count_button.apply {
            text = count.toString()
            setOnClickListener {
                count++
                Log.d("ushi_debug", "clicked $count times")
                text = count.toString()
            }
        }

        if (savedInstanceState == null) {
            val trans: FragmentTransaction = supportFragmentManager.beginTransaction()
            trans.add(R.id.fragment_container, SampleFragment(), "").commit()
        }

    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        Log.d("ushi_debug", "onSaveCount $count")
        outState?.putInt("mCount", count)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.d("ushi_debug", "call onRes")
        val mCount = savedInstanceState?.getInt("mCount", 0) ?: 0
        if (mCount != 0) {
            count_button.text = mCount.toString()
            count = mCount
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("ushi_debug", "onDest")
    }

    override fun onResume() {
        super.onResume()
        Log.d("ushi_debug", "onResume")
    }

}
