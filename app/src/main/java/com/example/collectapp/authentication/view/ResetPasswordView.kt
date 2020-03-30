package com.example.collectapp.authentication.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText

import com.example.collectapp.R
import com.example.collectapp.authentication.model.AuthenticationModel
import com.example.collectapp.authentication.model.AuthenticationProvider
import com.example.collectapp.authentication.presenter.AuthenticationResetPasswordPresenter
import com.example.collectapp.helper.BaseFragment
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.fragment_reset_password_view.*

class ResetPasswordView : BaseFragment<AuthenticationModel>(){

    override val layoutId: Int = R.layout.fragment_reset_password_view
    lateinit var presenter : AuthenticationResetPasswordPresenter
    var otp : EditText? = null
    var phone:EditText?= null
    var userPassword:EditText? = null

    override fun loadResponse(responseModel: AuthenticationModel) {

        var success = responseModel.success

        if (success) {
            // TODO: move to new activity
        }
        else {
            this.show(responseModel.message)
        }
    }

    override fun initView() {

        otp  = otpText
        phone = phoneNumber
        userPassword = password
        submit.setOnClickListener {
            click()
        }
    }

    private fun click() {
        var jsonObject = JsonObject()
        jsonObject.addProperty("phone",phone?.text.toString().toInt())
        jsonObject.addProperty("otp",otp?.text.toString().toInt())
        jsonObject.addProperty("password",password?.text.toString())
        presenter = AuthenticationResetPasswordPresenter(this, AuthenticationProvider(jsonObject))
        presenter.getResetPasswordResponse()
    }
}
