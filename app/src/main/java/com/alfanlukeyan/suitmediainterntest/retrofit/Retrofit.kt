package com.alfanlukeyan.suitmediainterntest.retrofit
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object Retrofit {

    val BASE_URL = "https://reqres.in/api/"

    fun getRetroData(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}