package com.example.sho.mvvm.rxkotlin.retrofit

import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

class QiitaClient {

    companion object {
        fun client(): QiitaItemService {
            val builder = Retrofit.Builder()
                    .client(OkHttpClient())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(MoshiConverterFactory.create(Moshi.Builder()
                            .build()))
                    .baseUrl(QiitaItemService.BASE_URL)
                    .build()
            return builder.create(QiitaItemService::class.java)
        }
    }
}