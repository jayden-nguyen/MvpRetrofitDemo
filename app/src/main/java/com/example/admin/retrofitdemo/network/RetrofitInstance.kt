package com.example.admin.retrofitdemo.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {
    companion object {
        var retrofit: Retrofit? = null
        val BASE_URL = "https://www.googleapis.com"

        val retrofitInstance: Retrofit
        get() {
            if (retrofit == null){
                retrofit = retrofit2.Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
            }
            return retrofit!!
        }
    }
}