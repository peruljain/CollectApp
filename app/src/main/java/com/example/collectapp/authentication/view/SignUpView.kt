package com.example.collectapp.authentication.view

import android.os.Bundle
import android.text.TextUtils
import android.widget.EditText
import androidx.navigation.fragment.findNavController

import com.example.collectapp.R
import com.example.collectapp.authentication.model.AuthenticationModel
import com.example.collectapp.authentication.model.AuthenticationProvider
import com.example.collectapp.authentication.presenter.AuthenticationSignUpPresenter
import com.example.collectapp.base.BaseFragment
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.fragment_sign_up.*
import kotlinx.android.synthetic.main.fragment_sign_up.view.*

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
            bundle.putString("phone", user_phone_number.text.toString())
            findNavController().navigate(R.id.action_signUpView_to_signUpOtpView,bundle)
        }
        else {
           this.show(responseModel.message)
        }
    }

    override fun initView() {
        // taking care of synthetics and ids
        user_name = userNameSignUp.userNameSignUpText
        user_password = passwordSignUp.passwordSignUpText
        user_phone_number = phoneNumberSignUp.phoneNumberSignUpText
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
        else if (this.checkPhone(user_phone_number.text.toString())){
            this.showLong("Please Enter Correct Phone Number")
        }
        else {
            jsonObject.addProperty("phone", user_phone_number.text.toString())
            jsonObject.addProperty("name", user_name.text.toString())
            jsonObject.addProperty("password", user_password.text.toString())
            signUpPresenter = AuthenticationSignUpPresenter(this, AuthenticationProvider(jsonObject))
            signUpPresenter.getSignUpResponse()
        }
    }
}
