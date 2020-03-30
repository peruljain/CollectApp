package com.example.collectapp.authentication.presenter

import com.example.collectapp.authentication.model.AuthenticationModel
import com.example.collectapp.authentication.model.AuthenticationProvider
import com.example.collectapp.authentication.view.ResetPasswordView
import com.example.collectapp.helper.PresenterCallback

class AuthenticationResetPasswordPresenter (var view : ResetPasswordView, val  provider : AuthenticationProvider) {

    open fun getResetPasswordResponse() {
        view.showProgressBar()
        provider.getUserResetPasswordResponse(object : PresenterCallback<AuthenticationModel> {

            override fun onSuccess(responseModel: AuthenticationModel) {
                view.hideProgressBar();
                view.loadResponse(responseModel);
            }
            override fun onFailure(message: String) {
                view show message
                view.hideProgressBar()
            }
        })
    }

}