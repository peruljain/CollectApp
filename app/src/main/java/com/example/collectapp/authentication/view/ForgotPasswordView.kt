package com.example.collectapp.authentication.view

import androidx.navigation.fragment.findNavController

import com.example.collectapp.R
import com.example.collectapp.authentication.model.AuthenticationModel
import com.example.collectapp.authentication.model.AuthenticationProvider
import com.example.collectapp.authentication.presenter.AuthenticationForgotPasswordPresenter
import com.example.collectapp.helper.BaseFragment
import com.google.gson.JsonObject


class ForgotPasswordView() : BaseFragment<AuthenticationModel>() {
    override val layoutId: Int = R.layout.fragment_forgot_password
    lateinit var presenter : AuthenticationForgotPasswordPresenter

    override fun loadResponse(responseModel: AuthenticationModel) {

        var success = responseModel.success

        if (success) {
            findNavController().navigate(R.id.action_forgotPasswordView_to_resetPasswordView)
        }
        else {
            this.show(responseModel.message)
        }
    }

    override fun initView() {
        var phone = arguments?.getLong("phone")
        var jsonObject = JsonObject()
        jsonObject.addProperty("phone",phone)
        presenter = AuthenticationForgotPasswordPresenter(this, AuthenticationProvider(jsonObject))
        presenter.getForgotPasswordResponse()
    }
}
