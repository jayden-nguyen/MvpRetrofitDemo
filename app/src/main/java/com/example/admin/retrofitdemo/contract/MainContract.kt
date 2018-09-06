package com.example.admin.retrofitdemo.contract

import com.example.admin.retrofitdemo.model.BookInfo
import java.util.ArrayList

class MainContract {
    interface MainView {
        fun showProgress()
        fun hideProgress()
        fun setDataToRecyclerView(bookList: ArrayList<BookInfo>?)
        fun onResponseFailure(throwable: Throwable)
    }

    interface MainPresenter {
        fun validateData(searchString: String):Boolean
        fun requestDataFromServer(searchString: String)
        fun onDestroy()
    }

    interface Interactor {
        interface OnFinishedListener {
            fun onFinished(bookList: ArrayList<BookInfo>?)
            fun onFailure(throwable: Throwable)
        }

        fun getBookList(onFinishedListener: OnFinishedListener, searchString: String)
    }
}