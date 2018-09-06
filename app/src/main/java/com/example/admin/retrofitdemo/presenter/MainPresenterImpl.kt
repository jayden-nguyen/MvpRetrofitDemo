package com.example.admin.retrofitdemo.presenter

import android.text.TextUtils
import com.example.admin.retrofitdemo.contract.MainContract
import com.example.admin.retrofitdemo.model.BookInfo
import java.util.ArrayList

class MainPresenterImpl(private var mainView: MainContract.MainView?, private var interactor: MainContract.Interactor) : MainContract.MainPresenter, MainContract.Interactor.OnFinishedListener {

    override fun onFinished(bookList: ArrayList<BookInfo>?) {
        if (mainView != null) {
            mainView!!.setDataToRecyclerView(bookList)
            mainView!!.hideProgress()
        }
    }

    override fun onFailure(throwable: Throwable) {
        if (mainView != null) {
            mainView!!.onResponseFailure(throwable)
            mainView!!.hideProgress()
        }
    }

    override fun requestDataFromServer(searchString: String) {
        interactor.getBookList(this, searchString)
    }

    override fun onDestroy() {
        mainView = null
    }

    override fun validateData(searchString: String):Boolean {
        return !TextUtils.isEmpty(searchString)
    }
}