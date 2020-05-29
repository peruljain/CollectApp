package com.example.collectapp.session.alltransactions.presenter

import com.example.collectapp.base.BasePresenter
import com.example.collectapp.helper.PresenterCallback
import com.example.collectapp.session.alltransactions.provider.AllTransactionProvider
import com.example.collectapp.session.alltransactions.provider.model.AllTransactionListModel
import com.example.collectapp.session.alltransactions.view.AllTransactionsListView

class AllTransactionListPresenter(var view: AllTransactionsListView) :
    BasePresenter() {
    val provider = AllTransactionProvider()

    fun getTransactionListResponse(sessionId: Long) {
        view.showProgressBar()
        provider.getAllTransactionListResponse(sessionId,
            object : PresenterCallback<AllTransactionListModel> {
                override fun onSuccess(responseModel: AllTransactionListModel) {
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
        super.onCleared()
        view.hideProgressBar()
    }

}