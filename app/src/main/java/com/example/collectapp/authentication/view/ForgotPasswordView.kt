package com.example.collectapp.authentication.view

import android.widget.Button
import android.widget.EditText
import androidx.navigation.fragment.findNavController

import com.example.collectapp.R
import com.example.collectapp.authentication.model.AuthenticationModel
import com.example.collectapp.authentication.model.AuthenticationProvider
import com.example.collectapp.authentication.presenter.AuthenticationForgotPasswordPresenter
import com.example.collectapp.base.BaseFragment
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.fragment_forgot_password.*
import kotlinx.android.synthetic.main.fragment_forgot_password.view.*


class ForgotPasswordView() : BaseFragment<AuthenticationModel>() {
    override val layoutId: Int = R.layout.fragment_forgot_password
    var presenter : AuthenticationForgotPasswordPresenter? = null
    lateinit var phoneNumber : EditText
    lateinit var submitButton : Button

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
        phoneNumber = forgotPhoneNumber.forgotPhoneNumberText
        submitButton = forgotSubmit
        submitButton.setOnClickListener {
            submit()
        }
    }
    private fun submit() {

        if(phoneNumber.text.isNullOrEmpty()) {
            this.show("Please enter correct phone details")
        }
        else {
        var phone = phoneNumber.text.toString()
        var jsonObject = JsonObject()
        jsonObject.addProperty("phone",phone)
        presenter = AuthenticationForgotPasswordPresenter(this, AuthenticationProvider(jsonObject))
        presenter?.getForgotPasswordResponse()
        }
    }
    override fun onDestroyView() {
        presenter?.onCleared()
        super.onDestroyView()
    }
}
