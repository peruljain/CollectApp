package com.example.collectapp.sessioncreate.presenter

import com.example.collectapp.helper.PresenterCallback
import com.example.collectapp.sessioncreate.provider.SessionCreateProvider
import com.example.collectapp.sessioncreate.provider.model.SessionListModel
import com.example.collectapp.sessioncreate.view.SessionListView

class SessionListPresenter (var view : SessionListView, var provider : SessionCreateProvider) {

    open fun getSessionListResponse() {
        view.showProgressBar();
        provider.getUserSessionListResponse(object  : PresenterCallback<SessionListModel> {
            override fun onSuccess(responseModel: SessionListModel) {
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