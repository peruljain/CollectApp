package com.example.collectapp.authentication.view


import android.content.Intent
import android.widget.Button
import android.widget.EditText
import com.example.collectapp.R
import com.example.collectapp.authentication.model.AuthenticationModel
import com.example.collectapp.authentication.model.AuthenticationProvider
import com.example.collectapp.authentication.presenter.AuthenticationSignUpOtpPresenter
import com.example.collectapp.helper.BaseFragment
import com.example.collectapp.sessioncreate.view.SessionListView
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.fragment_reset_password_view.view.*
import kotlinx.android.synthetic.main.fragment_sign_up_otp.*
import kotlinx.android.synthetic.main.fragment_sign_up_otp.view.*

class SignUpOtpView() : BaseFragment<AuthenticationModel>() {

    override val layoutId: Int  = R.layout.fragment_sign_up_otp
    var otp : EditText? = null
    lateinit var button: Button
    lateinit var presenter : AuthenticationSignUpOtpPresenter

    override fun loadResponse(responseModel: AuthenticationModel) {

        var success = responseModel.success

        if (success) {
            var intent = Intent(this.context, SessionListView::class.java)
            startActivity(intent)
            this.show("Register Successfully");
        }
        else {
            this.show(responseModel.message)
        }

    }

    override fun initView() {
        otp = submitSignUpOtp.submitSignUpOtpText
        button = otpButton
        button.setOnClickListener {
            submit()
        }
    }

    private fun submit() {

        if (!this.check(otp.toString())) {
            this.show("Please Enter OTP");
        }
        else {
            var jsonObject = JsonObject()
            jsonObject.addProperty("phone", arguments?.getString("phone"))
            jsonObject.addProperty("otp", otp?.text.toString().toInt())
            presenter = AuthenticationSignUpOtpPresenter(this, AuthenticationProvider(jsonObject))
            presenter.getSignUpOtpResponse()
        }
    }

}
