package com.example.collectapp.home.presenter

import com.example.collectapp.helper.GeneralModel
import com.example.collectapp.helper.PresenterCallback
import com.example.collectapp.home.provider.SessionListProvider
import com.example.collectapp.home.view.SessionJoinView

class SessionJoinPresenter (var view : SessionJoinView, var provider : SessionListProvider) {

    open fun getJoinReponse() {
        view.showProgressBar();
        provider.getUserSessionJoinResponse(object  : PresenterCallback<GeneralModel> {
            override fun onSuccess(responseModel: GeneralModel) {
                view.hideProgressBar()
                view.loadResponse(responseModel)
            }

            override fun onFailure(message: String) {
                view.show(message)
                view.hideProgressBar()
            }
        })
    }


}