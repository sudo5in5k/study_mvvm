package com.example.sho.mvvm.rxkotlin.retrofit

import io.reactivex.Observable
import retrofit2.http.GET

interface QiitaItemService {

    companion object {
        const val BASE_URL = "https://qiita.com/api/v2/"
    }

    @GET("items")
    fun items(): Observable<Array<QiitaItemResponse>>
}