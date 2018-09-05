package com.example.admin.retrofitdemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.example.admin.retrofitdemo.model.BookList
import com.example.admin.retrofitdemo.network.RetrofitInstance
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(),Callback<BookList> {
    override fun onFailure(call: Call<BookList>?, t: Throwable?) {
        Toast.makeText(this@MainActivity, "Error", Toast.LENGTH_LONG).show()
    }

    override fun onResponse(call: Call<BookList>?, response: Response<BookList>?) {
        if (response != null) {
            println("response is ${response.body()!!.items}")
        }
        if (response != null) {
            val adapter = BookAdapter(response.body()!!.items)
            rvBookList.adapter = adapter
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rvBookList.layoutManager = LinearLayoutManager(this)


        var dataAPI = RetrofitInstance.retrofitInstance.create(DataAPI::class.java)
        btnSearch.setOnClickListener {
            var call = dataAPI.getBookData(etSearch.text.toString())
            call.enqueue(this)
        }
    }
}
