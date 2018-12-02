package com.example.sho.mvvm.rxkotlin.retrofit

import java.io.Serializable

data class QiitaItemResponse(
        val id: String,
        val title: String,
        val body: String
) : Serializable