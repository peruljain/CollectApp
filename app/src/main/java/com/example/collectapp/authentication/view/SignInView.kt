package com.example.collectapp.authentication.view

import android.os.Bundle
import android.widget.EditText
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController

import com.example.collectapp.R
import com.example.collectapp.authentication.model.AuthenticationModel
import com.example.collectapp.authentication.model.AuthenticationProvider
import com.example.collectapp.authentication.presenter.AuthenticationSignInPresenter
import com.example.collectapp.helper.BaseFragment
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.fragment_sign_in.*
import kotlinx.android.synthetic.main.fragment_sign_up.*

class SignInView() : BaseFragment<AuthenticationModel>() {

    override val layoutId: Int = R.layout.fragment_sign_in
    var navController : NavController? = null;
    var userContactNumber : Int? = null
    var userLoginPassword : String? = null
    var  phoneNumber : EditText? = null
    var password : EditText? = null
    lateinit var signInPresenter : AuthenticationSignInPresenter

    override fun initView() {
        phoneNumber  = userPhoneNumber
        password = userPassword
        navController = findNavController()
        loginButton.setOnClickListener {
            loginCall()
        }
        
        signUpButton.setOnClickListener {
            navController!!.navigate(R.id.action_signInView_to_signUpView)
        }
        forgotPasswordClick.setOnClickListener {
            var bundle = Bundle()
            bundle.putInt("phone",phoneNumber!!.text.toString().toInt())
            navController!!.navigate(R.id.action_signInView_to_forgotPasswordView,bundle)
        }
    }

    override fun loadResponse(responseResponseModel: AuthenticationModel) {
        // response coming from presenter
        // reponse coming from provider to presenter

        var success = responseResponseModel.success

        if (success) {
            // move to activity
        }
        else {
            // show message
        }

    }

    private fun loginCall() {
        userContactNumber = phoneNumber!!.text.toString().toInt()
        userLoginPassword = password!!.text.toString()
        if (userContactNumber == null || userLoginPassword == null) {
            this.showLong("Please Enter all details")
        } else {
            val jsonObject = JsonObject()
            jsonObject.addProperty("phone", userContactNumber)
            jsonObject.addProperty("password", userLoginPassword)
            signInPresenter = AuthenticationSignInPresenter(this, AuthenticationProvider(jsonObject))
            signInPresenter.getSignInResponse()
        }
    }


}
