package com.example.collectapp.authentication.view

import android.view.View
import android.widget.EditText

import com.example.collectapp.R
import com.example.collectapp.authentication.model.AuthenticationModel
import com.example.collectapp.authentication.model.AuthenticationProvider
import com.example.collectapp.authentication.presenter.AuthenticationSignInPresenter
import com.example.collectapp.helper.BaseFragment
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.fragment_sign_in.*

class SignInView() : BaseFragment<AuthenticationModel>() {

    override val layoutId: Int = R.id.sign_in
    var userContactNumber : Long? = null
    var userLoginPassword : String? = null
    lateinit  var  phoneNumber : EditText
    lateinit var password : EditText
    lateinit var signInPresenter : AuthenticationSignInPresenter

    override fun initView() {
        phoneNumber  = userPhoneNumber
        password = userPassword
    }

    override fun loadResponse(responseResponseModel: AuthenticationModel) {
        // response coming from presenter
        // reponse coming from provider to presenter
    }

    fun login(v : View) {
        userContactNumber = phoneNumber.text.toString().toLong()
        userLoginPassword = password.text.toString()
        if (userContactNumber == null || userLoginPassword == null) {
            this.showLong("Please Enter all details")
        } else {
            val jsonObject = JsonObject()
            jsonObject.addProperty("phoneNumber", userContactNumber)
            jsonObject.addProperty("password", userLoginPassword)
            signInPresenter = AuthenticationSignInPresenter(this, AuthenticationProvider(jsonObject))
            signInPresenter.getSignInResponse()
        }
    }
}
