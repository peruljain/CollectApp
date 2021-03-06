package com.example.collectapp.home.presenter

import com.example.collectapp.base.BasePresenter
import com.example.collectapp.helper.GeneralModel
import com.example.collectapp.helper.PresenterCallback
import com.example.collectapp.home.provider.SessionListProvider
import com.example.collectapp.home.view.SessionJoinView

class SessionJoinPresenter (var view : SessionJoinView, var provider : SessionListProvider) : BasePresenter() {

     fun getJoinReponse() {
        view.showProgressBar()
        provider.getUserSessionJoinResponse(object  : PresenterCallback<GeneralModel> {
            override fun onSuccess(responseModel: GeneralModel) {
                view.hideProgressBar()
                view.loadResponse(responseModel)
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