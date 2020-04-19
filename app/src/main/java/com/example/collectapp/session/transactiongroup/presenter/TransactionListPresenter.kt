package com.example.collectapp.session.transactiongroup.presenter

import com.example.collectapp.helper.PresenterCallback
import com.example.collectapp.session.transactiongroup.provider.TransactionGroupProvider
import com.example.collectapp.session.transactiongroup.provider.model.TransactionListModel
import com.example.collectapp.session.transactiongroup.view.TransactionListView

class TransactionListPresenter(var view : TransactionListView, var provider : TransactionGroupProvider){

    open fun getTransactionGroupListResponse() {
        view.showProgressBar();
        provider.getUserListGroupResponse(object  : PresenterCallback<TransactionListModel>{
            override fun onSuccess(responseModel: TransactionListModel) {
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