package com.example.collectapp.session.alltransactions.presenter

import com.example.collectapp.helper.PresenterCallback
import com.example.collectapp.home.provider.model.SessionListModel
import com.example.collectapp.session.alltransactions.provider.AllTransactionProvider
import com.example.collectapp.session.alltransactions.provider.model.AllTransactionListModel
import com.example.collectapp.session.alltransactions.view.AllTransactionsListView

class AllTransactionListPresenter(var view : AllTransactionsListView, var  provider : AllTransactionProvider) {
    open fun getTransactionListResponse(){
        view.showProgressBar();
        provider.getAllTransactionListResponse(object  : PresenterCallback<AllTransactionListModel> {
            override fun onSuccess(responseModel: AllTransactionListModel) {
                view.show(responseModel.message)
                view.loadResponse(responseModel)
                view.hideProgressBar()
            }

            override fun onFailure(message: String) {
                view.show(message)
                view.hideProgressBar()
            }
        })
    }
}