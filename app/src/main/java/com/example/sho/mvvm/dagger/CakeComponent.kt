package com.example.sho.mvvm.dagger

import com.example.sho.mvvm.MainActivity
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

/**
 * Component DIさせる対象を指定する
 *
 * Created by sho on 2018/11/19.
 */
@Singleton
@Component(modules = arrayOf(AndroidInjectionModule::class, ChocolateCakeModule::class))
interface CakeComponent {

    // injectする対象を引数にすることで自動生成されるクラスのメソッド内でメンバへの注入処理が実装される
    // injectである必要がないがこれがわかりやすいか
    fun inject(activity: MainActivity)
}