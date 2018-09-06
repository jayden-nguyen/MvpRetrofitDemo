package com.example.admin.retrofitdemo.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Gravity
import android.view.View
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.Toast
import com.example.admin.retrofitdemo.R
import com.example.admin.retrofitdemo.adapter.BookAdapter
import com.example.admin.retrofitdemo.contract.MainContract
import com.example.admin.retrofitdemo.interator.InteratorImpl
import com.example.admin.retrofitdemo.model.BookInfo
import com.example.admin.retrofitdemo.presenter.MainPresenterImpl
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity(), View.OnClickListener, MainContract.MainView {

    private lateinit var mainPresenter: MainContract.MainPresenter
    private lateinit var progressBar: ProgressBar
    override fun showProgress() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressBar.visibility = View.INVISIBLE
    }

    override fun setDataToRecyclerView(bookList: ArrayList<BookInfo>?) {
        val adapter = BookAdapter(bookList)
        rvBookList.adapter = adapter
    }

    override fun onResponseFailure(throwable: Throwable) {
        Toast.makeText(this@MainActivity, "Something went wrong " + throwable.message, Toast.LENGTH_LONG).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initProgressBar()
        initRecyclerView()

        mainPresenter = MainPresenterImpl(this, InteratorImpl())
        btnSearch.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (mainPresenter.validateData(etSearch.text.toString())) {
            showProgress()
            mainPresenter.requestDataFromServer(etSearch.text.toString())
        } else {
            Toast.makeText(this@MainActivity, "Please fill it", Toast.LENGTH_LONG).show()
        }
    }

    private fun initProgressBar() {
        progressBar = ProgressBar(this, null, android.R.attr.progressBarStyleLarge)
        progressBar.isIndeterminate = true
        val relativeLayout = RelativeLayout(this)
        relativeLayout.gravity = Gravity.CENTER
        relativeLayout.addView(progressBar)
        val params = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT)
        progressBar.visibility = View.INVISIBLE
        this.addContentView(relativeLayout, params)
    }

    private fun initRecyclerView() {
        rvBookList.layoutManager = LinearLayoutManager(this)
    }
}
