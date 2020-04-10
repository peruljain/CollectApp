package com.example.collectapp.authentication.view

import android.content.Intent
import android.widget.EditText
import com.example.collectapp.R
import com.example.collectapp.authentication.model.AuthenticationModel
import com.example.collectapp.authentication.model.AuthenticationProvider
import com.example.collectapp.authentication.presenter.AuthenticationResetPasswordPresenter
import com.example.collectapp.base.BaseFragment
import com.example.collectapp.helper.Constants
import com.example.collectapp.helper.SharedPref
import com.example.collectapp.home.view.HomeActivity
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.fragment_reset_password_view.*
import kotlinx.android.synthetic.main.fragment_reset_password_view.view.*

class ResetPasswordView : BaseFragment<AuthenticationModel>() {

    override val layoutId: Int = R.layout.fragment_reset_password_view
    lateinit var presenter: AuthenticationResetPasswordPresenter
    var otp: EditText? = null
    var phone: EditText? = null
    var userPassword: EditText? = null

    override fun loadResponse(responseModel: AuthenticationModel) {

        var success = responseModel.success

        if (success) {
            // TODO: move to new activity
            this.show(responseModel.message)
            SharedPref.putString(Constants.phoneNumber, arguments?.getString("phone")!!)
            SharedPref.putString(Constants.authorization, responseModel.access_token)
            val intent = Intent(this.context, HomeActivity::class.java)
            startActivity(intent)
            activity!!.finish()
        } else {
            this.show(responseModel.message)
        }
    }

    override fun initView() {

        otp = resetPasswordOtp.resetPasswordOtpText
        phone = resetphoneNumber.resetphoneNumberText
        userPassword = resetPassword.resetPasswordText
        submit.setOnClickListener {
            click()
        }
    }

    private fun click() {

        if (!this.check(phone?.text.toString()) || !this.check(otp?.text.toString()) || !this.check(userPassword?.text.toString())) {
            this.show("Please Enter Correct Details");
        }
        else {
            var jsonObject = JsonObject()
            jsonObject.addProperty("phone", phone?.text.toString())
            jsonObject.addProperty("otp", otp?.text.toString().toInt())
            jsonObject.addProperty("password", userPassword?.text.toString())
            presenter =
                AuthenticationResetPasswordPresenter(this, AuthenticationProvider(jsonObject))
            presenter.getResetPasswordResponse()
        }
    }
}
