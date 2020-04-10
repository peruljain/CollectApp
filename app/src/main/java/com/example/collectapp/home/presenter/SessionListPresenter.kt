package com.example.collectapp.home.presenter

import com.example.collectapp.helper.PresenterCallback
import com.example.collectapp.home.provider.SessionListProvider
import com.example.collectapp.home.provider.model.SessionListModel
import com.example.collectapp.home.view.SessionListView

class SessionListPresenter (var view : SessionListView, var provider : SessionListProvider) {

    open fun getSessionListResponse() {
        view.showProgressBar();
        provider.getUserSessionListResponse(object  : PresenterCallback<SessionListModel> {
            override fun onSuccess(responseModel: SessionListModel) {
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