package com.example.sho.mvvm.rxkotlin.retrofit

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class QiitaClient {

    companion object {
        fun client(): QiitaItemService {
            val builder = Retrofit.Builder()
                    .client(OkHttpClient())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(MoshiConverterFactory.create(Moshi.Builder()
                            .build()))
                    .baseUrl(QiitaItemService.BASE_URL)
                    .build()
            return builder.create(QiitaItemService::class.java)
        }
    }
}