package com.example.collectapp.session.members.presenter

import com.example.collectapp.helper.PresenterCallback
import com.example.collectapp.session.members.provider.SessionMemberProvider
import com.example.collectapp.session.members.provider.model.MembersListModel
import com.example.collectapp.session.members.view.MembersListView
import timber.log.Timber

class MemberListPresenter(var view : MembersListView, var provider: SessionMemberProvider) {

    fun getMemberListResponse() {
        view.showProgressBar()
        provider.getUserMembersListResponse(object  : PresenterCallback<MembersListModel> {
            override fun onSuccess(responseModel: MembersListModel) {
                view.show(responseModel.message)
                Timber.d("responseModel.data ${responseModel.data}")
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