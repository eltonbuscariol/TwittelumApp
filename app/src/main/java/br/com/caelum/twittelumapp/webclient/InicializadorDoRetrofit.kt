package br.com.caelum.twittelumapp.webclient

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object InicializadorDoRetrofit {

    val retrofit : Retrofit = Retrofit.Builder()
        .baseUrl("https://c118dcc8.ngrok.io")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}