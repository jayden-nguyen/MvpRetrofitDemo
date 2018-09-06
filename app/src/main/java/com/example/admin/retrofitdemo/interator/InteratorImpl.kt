package com.example.admin.retrofitdemo.interator

import com.example.admin.retrofitdemo.DataAPI
import com.example.admin.retrofitdemo.contract.MainContract
import com.example.admin.retrofitdemo.model.BookList
import com.example.admin.retrofitdemo.network.RetrofitInstance
import retrofit2.Call
import retrofit2.Response

class InteratorImpl : MainContract.Interactor {
    override fun getBookList(onFinishedListener: MainContract.Interactor.OnFinishedListener, searchString: String) {
        val dataAPI = RetrofitInstance.retrofitInstance.create(DataAPI::class.java)
        val call = dataAPI.getBookData(searchString)
        call.enqueue(object : retrofit2.Callback<BookList> {
            override fun onResponse(call: Call<BookList>, response: Response<BookList>) {
                onFinishedListener.onFinished(response.body()!!.items!!)
            }

            override fun onFailure(call: Call<BookList>, t: Throwable) {
                onFinishedListener.onFailure(t)
            }
        })
    }

}