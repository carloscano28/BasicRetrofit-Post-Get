package com.example.config

import android.app.Application
import com.example.models.Providers

class InicioAplicacion : Application(){

    // Cada objeto dentro del companion puede ser accedido en toda la app
    companion object{

        lateinit var webServiceGlobal: Providers

    }


    override fun onCreate() {
        super.onCreate()

        webServiceGlobal=RetrofitConfig().obtenerConfiguracionRetrofit()
    }
}