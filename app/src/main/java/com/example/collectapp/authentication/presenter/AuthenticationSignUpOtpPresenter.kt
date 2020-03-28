package com.example.collectapp.authentication.presenter

import com.example.collectapp.authentication.model.AuthenticationModel
import com.example.collectapp.authentication.model.AuthenticationProvider
import com.example.collectapp.authentication.view.SignUpOtpView
import com.example.collectapp.helper.PresenterCallback

class AuthenticationSignUpOtpPresenter (var view : SignUpOtpView, var provider: AuthenticationProvider) {

    open fun getSignUpOtpResponse() {
        view.showProgressBar()
        provider.getUserSignUpOtpResponse(object : PresenterCallback<AuthenticationModel> {

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