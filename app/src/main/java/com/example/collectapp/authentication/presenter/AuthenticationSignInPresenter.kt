package com.example.collectapp.authentication.presenter

import com.example.collectapp.authentication.model.AuthenticationModel
import com.example.collectapp.authentication.model.AuthenticationProvider
import com.example.collectapp.authentication.model.UserModel
import com.example.collectapp.authentication.view.SignInView
import com.example.collectapp.helper.PresenterCallback

class AuthenticationSignInPresenter(var view: SignInView, var provider : AuthenticationProvider) {

    open fun getSignInResponse() {
        view.showProgressBar()
        provider.getUserSignInResponse(object : PresenterCallback<AuthenticationModel> {

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