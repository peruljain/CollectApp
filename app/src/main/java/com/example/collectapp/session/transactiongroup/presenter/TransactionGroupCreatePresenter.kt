package com.example.collectapp.session.transactiongroup.presenter

import com.example.collectapp.helper.PresenterCallback
import com.example.collectapp.session.transactiongroup.provider.TransactionGroupProvider
import com.example.collectapp.session.transactiongroup.provider.model.TransactionCreateModel
import com.example.collectapp.session.transactiongroup.provider.model.TransactionListModel
import com.example.collectapp.session.transactiongroup.view.TransactionGroupCreateView

class TransactionGroupCreatePresenter(var view : TransactionGroupCreateView, var provider : TransactionGroupProvider) {
    open fun getTransactionGroupCreateResponse() {
        view.showProgressBar();
        provider.getUserCreateGroupResponse(object  : PresenterCallback<TransactionCreateModel> {
            override fun onSuccess(responseModel: TransactionCreateModel) {
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