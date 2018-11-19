package com.example.sho.mvvm.dagger

import dagger.Module
import dagger.Provides

/**
 * Module DIするクラスを指定する
 *
 * Created by sho on 2018/11/19.
 */

@Module
class ChocolateCakeModule {

    @Provides
    fun provideChocolateCake(): ChocolateCake = ChocolateCake()
}