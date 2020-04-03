package com.example.collectapp.authentication.view

import android.os.Bundle
import android.widget.EditText
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController

import com.example.collectapp.R
import com.example.collectapp.authentication.model.AuthenticationModel
import com.example.collectapp.authentication.model.AuthenticationProvider
import com.example.collectapp.authentication.presenter.AuthenticationSignInPresenter
import com.example.collectapp.helper.BaseFragment
import com.google.android.material.textfield.TextInputEditText
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.fragment_sign_in.*
import kotlinx.android.synthetic.main.fragment_sign_in.view.*

class SignInView() : BaseFragment<AuthenticationModel>() {

    override val layoutId: Int = R.layout.fragment_sign_in
    var navController : NavController? = null;
    var userContactNumber : Long? = null
    var userLoginPassword : String? = null
    var  phoneNumber : TextInputEditText? = null
    var password : TextInputEditText? = null
    lateinit var signInPresenter : AuthenticationSignInPresenter

    override fun initView() {
        phoneNumber  = userPhoneNumber.userPhoneNumberText
        password = userPassword.userPasswordText
        navController = view!!.findNavController()
        loginButton.setOnClickListener {
            loginCall()
        }
        SignUpNavButton.setOnClickListener {
            navController!!.navigate(R.id.action_signInView_to_signUpView)
        }
        forgotPasswordClick.setOnClickListener {
            navController!!.navigate(R.id.action_signInView_to_forgotPasswordView)
        }
    }

    override fun loadResponse(responseResponseModel: AuthenticationModel) {
        // response coming from presenter
        // reponse coming from provider to presenter

        var success = responseResponseModel.success

        if (success) {
            // move to activity
            this.showLong(responseResponseModel.message);
        }
        else {
           this.show(responseResponseModel.message)
        }

    }

    private fun loginCall() {

        if (phoneNumber!!.text == null || password!!.text == null) {
            this.showLong("Please Enter all details")
        } else {
            userContactNumber = phoneNumber!!.text.toString().toLong()
            userLoginPassword = password!!.text.toString()
            val jsonObject = JsonObject()
            jsonObject.addProperty("phone", userContactNumber)
            jsonObject.addProperty("password", userLoginPassword)
            signInPresenter = AuthenticationSignInPresenter(this, AuthenticationProvider(jsonObject))
            signInPresenter.getSignInResponse()
        }
    }


}
