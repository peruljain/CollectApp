package com.example.collectapp.session.transactiongroup.presenter

import com.example.collectapp.base.BasePresenter
import com.example.collectapp.helper.PresenterCallback
import com.example.collectapp.session.transactiongroup.provider.TransactionGroupProvider
import com.example.collectapp.session.transactiongroup.provider.model.TransactionCreateModel
import com.example.collectapp.session.transactiongroup.view.TransactionGroupCreateView
import com.google.gson.JsonObject

class TransactionGroupCreatePresenter(var view : TransactionGroupCreateView) : BasePresenter() {
    val provider = TransactionGroupProvider()
    fun getTransactionGroupCreateResponse(jsonObject: JsonObject) {
        view.showProgressBar()
        provider.getUserCreateGroupResponse(jsonObject, object  : PresenterCallback<TransactionCreateModel> {
            override fun onSuccess(responseModel: TransactionCreateModel) {
                view.show(responseModel.message)
                view.loadResponse(responseModel)
                view.hideProgressBar()
            }
            override fun onFailure(message: String) {
                view.show(message)
                view.hideProgressBar()
            }
        }).also { compositeDisposable.add(it) }
    }
    override fun onCleared() {
        view.hideProgressBar()
        super.onCleared()
    }
}