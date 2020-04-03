package com.example.collectapp.authentication.view

import android.widget.Button
import android.widget.EditText
import androidx.navigation.fragment.findNavController

import com.example.collectapp.R
import com.example.collectapp.authentication.model.AuthenticationModel
import com.example.collectapp.authentication.model.AuthenticationProvider
import com.example.collectapp.authentication.presenter.AuthenticationForgotPasswordPresenter
import com.example.collectapp.helper.BaseFragment
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.fragment_forgot_password.*


class ForgotPasswordView() : BaseFragment<AuthenticationModel>() {
    override val layoutId: Int = R.layout.fragment_forgot_password
    lateinit var presenter : AuthenticationForgotPasswordPresenter
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
        phoneNumber = forgotPhoneNumber
        submitButton = forgotSubmit
        submitButton.setOnClickListener {
            submit();
        }
    }
    private fun submit() {
        var phone = phoneNumber.text.toString().toLong()
        var jsonObject = JsonObject()
        jsonObject.addProperty("phone",phone)
        presenter = AuthenticationForgotPasswordPresenter(this, AuthenticationProvider(jsonObject))
        presenter.getForgotPasswordResponse()
    }
}
