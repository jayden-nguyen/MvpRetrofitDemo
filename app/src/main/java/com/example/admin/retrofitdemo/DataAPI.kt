package com.example.admin.retrofitdemo

import com.example.admin.retrofitdemo.model.BookList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface DataAPI {
    @GET("books/v1/volumes")
    fun getBookData(@Query("q") q: String): Call<BookList>
}