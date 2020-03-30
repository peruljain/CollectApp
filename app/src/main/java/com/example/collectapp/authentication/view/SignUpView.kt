package com.example.collectapp.authentication.view

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController

import com.example.collectapp.R
import com.example.collectapp.authentication.model.AuthenticationModel
import com.example.collectapp.authentication.model.AuthenticationProvider
import com.example.collectapp.authentication.presenter.AuthenticationSignInPresenter
import com.example.collectapp.authentication.presenter.AuthenticationSignUpPresenter
import com.example.collectapp.helper.BaseFragment
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.fragment_sign_in.*
import kotlinx.android.synthetic.main.fragment_sign_up.*

class SignUpView : BaseFragment<AuthenticationModel>() {

    override val layoutId: Int = R.layout.fragment_sign_up
    private lateinit var signUpPresenter : AuthenticationSignUpPresenter

    lateinit var user_name : EditText
    lateinit var user_password : EditText
    lateinit var user_phone_number : EditText


    override fun loadResponse(responseModel: AuthenticationModel) {
        // response model;
        var success = responseModel.success

        if (success) {
            var bundle = Bundle()
            bundle.putInt("phone", user_phone_number.text.toString().toInt())
            findNavController().navigate(R.id.action_signUpView_to_signUpOtpView)
        }
        else {
           this.show(responseModel.message)
        }
    }

    override fun initView() {
        user_name = userName
        user_password = userPassword
        user_phone_number = userPhoneNumber
        signUpButton.setOnClickListener {
            signUp()
        }
    }

    private fun signUp() {
        var jsonObject = JsonObject()
        if (TextUtils.isEmpty(user_name.text) || TextUtils.isEmpty(user_password.text) ||
                                                    TextUtils.isEmpty(user_phone_number.text)) {
            this.showLong("Please enter all details")
        }
        else {
            jsonObject.addProperty("phone", user_phone_number.text.toString().toInt())
            jsonObject.addProperty("name", user_name.text.toString())
            jsonObject.addProperty("password", user_password.text.toString())
            signUpPresenter = AuthenticationSignUpPresenter(this, AuthenticationProvider(jsonObject))
            signUpPresenter.getSignUpResponse()
        }
    }


}
