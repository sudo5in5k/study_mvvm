package com.example.sho.mvvm.dagger

import android.util.Log

/**
 * 具象クラス
 *
 * Created by sho on 2018/11/19.
 */
class ChocolateCake : Cake {
    override fun make() {
        Log.d("ushi_debug", "make chocolate cake!")
    }

}