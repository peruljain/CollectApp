package com.example.collectapp.authentication.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.navigation.fragment.findNavController

import com.example.collectapp.R
import com.example.collectapp.authentication.model.AuthenticationModel
import com.example.collectapp.authentication.model.AuthenticationProvider
import com.example.collectapp.authentication.presenter.AuthenticationSignUpOtpPresenter
import com.example.collectapp.helper.BaseFragment
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.fragment_sign_up_otp.*

class SignUpOtpView() : BaseFragment<AuthenticationModel>() {

    override val layoutId: Int  = R.layout.fragment_sign_up
    var otp : EditText? = null
    lateinit var presenter : AuthenticationSignUpOtpPresenter

    override fun loadResponse(responseModel: AuthenticationModel) {

        var success = responseModel.success

        if (success) {
            //TODO: proceed to new activity
        }
        else {
            this.show(responseModel.message)
        }

    }

    override fun initView() {
        otp = otpText
        otpButton.setOnClickListener {
            submit()
        }
    }

    private fun submit() {
        var jsonObject = JsonObject()
        jsonObject.addProperty("phone", arguments?.getInt("phone"))
        jsonObject.addProperty("otp", otp?.text.toString().toInt())
        presenter = AuthenticationSignUpOtpPresenter(this, AuthenticationProvider(jsonObject))
        presenter.getSignUpOtpResponse()
    }

}
