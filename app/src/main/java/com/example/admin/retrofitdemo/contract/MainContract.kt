package com.example.admin.retrofitdemo.contract

class MainContract {
    interface MainView {
        fun showProgress()
        fun hideProgress()
        fun setDataToRecyclerView()

    }

    interface MainPresenter {
        fun requestDataFromServer()
    }

    interface Interactor {

    }
}