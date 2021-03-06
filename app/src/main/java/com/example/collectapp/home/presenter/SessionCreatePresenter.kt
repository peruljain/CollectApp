package com.example.collectapp.home.presenter

import com.example.collectapp.base.BasePresenter
import com.example.collectapp.helper.PresenterCallback
import com.example.collectapp.home.provider.SessionListProvider
import com.example.collectapp.home.provider.model.SessionCreateModel
import com.example.collectapp.home.view.SessionCreateView

class SessionCreatePresenter (var view : SessionCreateView, val provider : SessionListProvider) : BasePresenter() {

     fun getSessionCreateResponse() {
        view.showProgressBar()

        provider.getUserSessionCreateResponse(object  : PresenterCallback<SessionCreateModel>{
            override fun onSuccess(responseModel: SessionCreateModel) {
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