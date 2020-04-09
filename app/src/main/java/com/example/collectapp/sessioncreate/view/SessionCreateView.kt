package com.example.collectapp.sessioncreate.view

import android.widget.Button
import android.widget.EditText
import com.example.collectapp.R
import com.example.collectapp.helper.BaseDialogFragment
import com.example.collectapp.helper.Constants
import com.example.collectapp.helper.SharedPref
import com.example.collectapp.sessioncreate.presenter.SessionCreatePresenter
import com.example.collectapp.sessioncreate.provider.SessionCreateProvider
import com.example.collectapp.sessioncreate.provider.model.SessionCreateModel
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.fragment_session_create_view.*


class SessionCreateView :  BaseDialogFragment<SessionCreateModel>() {

    override val layoutId: Int = R.layout.fragment_session_create_view
    lateinit var sessionName : EditText
    lateinit var submit : Button
    lateinit var cancel : Button
    lateinit var presenter : SessionCreatePresenter

    override fun loadResponse(responseModel: SessionCreateModel) {

        if (responseModel.success) {
            var sessionId = responseModel.data.sessionID
            var sessionToken = responseModel.data.sessionToken
            SharedPref.putLong(Constants.session_ID,sessionId)
            SharedPref.putString(Constants.session_Token,sessionToken!!)
            this.show(responseModel.message)
        }
        else {
            this.show(responseModel.message)
        }
        // To dismiss the fragment
        dismiss()
    }

    override fun initView() {

        sessionName = sessionNameCreate
        submit = submitSessionCreate
        cancel = cancelButtonResponse
        submit.setOnClickListener {
            submitClick()
        }
        cancel.setOnClickListener {
            cancelClick()
        }
    }

    private fun cancelClick() {
        dismiss()
    }

    private fun submitClick() {

        var jsonObject = JsonObject()
        jsonObject.addProperty("sessionName", sessionName.text.toString())
        var header = SharedPref.getString(Constants.authorization)
        presenter = SessionCreatePresenter(this, SessionCreateProvider(header!!, jsonObject))
        presenter.getSessionCreateResponse()
    }


}

