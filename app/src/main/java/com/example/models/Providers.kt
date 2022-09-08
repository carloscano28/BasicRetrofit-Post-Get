package com.example.models

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface Providers{


    @GET("posts")
    fun recuperaPublicaciones():Call<ArrayList<Publicacion>>

    @POST("posts")
    fun instertarPublicacion(@Body body: Publicacion):Call<Publicacion>

}