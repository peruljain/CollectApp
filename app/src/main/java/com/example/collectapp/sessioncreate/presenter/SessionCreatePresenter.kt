package com.example.collectapp.sessioncreate.presenter

import com.example.collectapp.helper.PresenterCallback
import com.example.collectapp.sessioncreate.provider.SessionCreateProvider
import com.example.collectapp.sessioncreate.provider.model.SessionCreateModel
import com.example.collectapp.sessioncreate.view.SessionCreateView

class SessionCreatePresenter (var view : SessionCreateView, val provider : SessionCreateProvider){

    open fun getSessionCreateResponse() {
        view.showProgressBar();

        provider.getUserSessionCreateResponse(object  : PresenterCallback<SessionCreateModel>{
            override fun onSuccess(responseModel: SessionCreateModel) {
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