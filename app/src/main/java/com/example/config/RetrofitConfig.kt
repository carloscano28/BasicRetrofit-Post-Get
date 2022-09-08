package com.example.config

import com.example.models.Providers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitConfig {
    val URL_BASE = "https://jsonplaceholder.typicode.com/"
    fun obtenerConfiguracionRetrofit():Providers{

        var mRetrofit= Retrofit.Builder(
        )
            .baseUrl(URL_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return mRetrofit.create(Providers::class.java)
    }



}