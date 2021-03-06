package com.example.collectapp.authentication.view

import android.content.Intent
import androidx.navigation.NavController
import androidx.navigation.findNavController

import com.example.collectapp.R
import com.example.collectapp.authentication.model.AuthenticationModel
import com.example.collectapp.authentication.model.AuthenticationProvider
import com.example.collectapp.authentication.presenter.AuthenticationSignInPresenter
import com.example.collectapp.base.BaseFragment
import com.example.collectapp.helper.Constants
import com.example.collectapp.helper.SharedPref
import com.example.collectapp.home.view.HomeActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.fragment_sign_in.*
import kotlinx.android.synthetic.main.fragment_sign_in.view.*

class SignInView() : BaseFragment<AuthenticationModel>() {

    override val layoutId: Int = R.layout.fragment_sign_in
    var navController : NavController? = null
    var userContactNumber : String? = null
    var userLoginPassword : String? = null
    lateinit var  phoneNumber : TextInputEditText
    lateinit var password : TextInputEditText
    var signInPresenter : AuthenticationSignInPresenter? = null

    override fun initView() {
        phoneNumber  = userPhoneNumber.userPhoneNumberText
        password = userPassword.userPasswordText
        navController = requireView().findNavController()
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

    override fun loadResponse(responseModel: AuthenticationModel) {
        // response coming from presenter
        // reponse coming from provider to presenter

        val success = responseModel.success

        if (success) {
            // move to activity
            this.showLong(responseModel.access_token)
            SharedPref.putString(Constants.AUTHORIZATION, responseModel.access_token)
            SharedPref.putString(Constants.PHONE_NUMBER, phoneNumber.text.toString())
            val intent = Intent(this.context, HomeActivity::class.java)
            startActivity(intent)
            requireActivity().finish()

        }
        else {
           this.show(responseModel.message)
        }

    }

    private fun loginCall() {

        if (!this.check(phoneNumber.text.toString()) || !this.check(password.text.toString())) {
            this.showLong("Please Enter all details")
        }
        else if (this.checkPhone(phoneNumber.text.toString())){
            this.showLong("Please Enter Correct Phone Number")
        }
        else {
            userContactNumber = phoneNumber.text.toString()
            userLoginPassword = password.text.toString()
            val jsonObject = JsonObject()
            jsonObject.addProperty("phone", userContactNumber)
            jsonObject.addProperty("password", userLoginPassword)
            signInPresenter = AuthenticationSignInPresenter(this, AuthenticationProvider(jsonObject))
            signInPresenter?.getSignInResponse()
        }
    }
    override fun onDestroyView() {
        signInPresenter?.onCleared()
        super.onDestroyView()
    }

}
