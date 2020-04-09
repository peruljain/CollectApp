package com.example.collectapp.sessioncreate.presenter

import com.example.collectapp.helper.GeneralModel
import com.example.collectapp.helper.PresenterCallback
import com.example.collectapp.sessioncreate.provider.SessionCreateProvider
import com.example.collectapp.sessioncreate.provider.model.SessionCreateModel
import com.example.collectapp.sessioncreate.view.SessionJoinView

class SessionJoinPresenter (var view : SessionJoinView, var provider : SessionCreateProvider) {

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