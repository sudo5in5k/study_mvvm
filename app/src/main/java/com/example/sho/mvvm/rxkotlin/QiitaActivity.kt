package com.example.sho.mvvm.rxkotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.sho.mvvm.R

class QiitaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qiita)

        supportFragmentManager.beginTransaction()
                .replace(R.id.qiita_list_container, QiitaListFragment())
                .addToBackStack(null)
                .commit()
    }
}