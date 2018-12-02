package com.example.sho.mvvm.rxkotlin.retrofit

import retrofit2.http.GET
import rx.Observable

interface QiitaItemService {

    companion object {
        const val BASE_URL = "https://qiita.com/api/v2/"
    }

    @GET("items")
    fun items(): Observable<Array<QiitaItemResponse>>
}