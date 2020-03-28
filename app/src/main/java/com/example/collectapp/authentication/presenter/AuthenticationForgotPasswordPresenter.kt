package com.example.collectapp.authentication.presenter

import com.example.collectapp.authentication.model.AuthenticationModel
import com.example.collectapp.authentication.model.AuthenticationProvider
import com.example.collectapp.authentication.view.ForgotPasswordView
import com.example.collectapp.helper.PresenterCallback

class AuthenticationForgotPasswordPresenter(var view : ForgotPasswordView, var provider : AuthenticationProvider) {

    open fun getForgotPasswordResponse() {
        view.showProgressBar()
        provider.getUserForgotPasswordResponse(object : PresenterCallback<AuthenticationModel> {

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