package com.example.sho.mvvm

import android.database.Observable
import retrofit2.http.GET


/**
 * サーバからのデータを返すインターフェス
 *
 * Created by sho on 2018/10/30.
 */
interface PostApi {
    /**
     * apiから結果のリストを取得する
     */
    @GET("/posts")
    fun getPosts(): Observable<List<Post>>
}