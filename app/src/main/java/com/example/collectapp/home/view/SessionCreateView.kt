package com.example.collectapp.home.view

import android.widget.Button
import com.example.collectapp.R
import com.example.collectapp.base.BaseDialogFragment
import com.example.collectapp.base.BaseFragment
import com.example.collectapp.helper.Constants
import com.example.collectapp.helper.SharedPref
import com.example.collectapp.home.presenter.SessionCreatePresenter
import com.example.collectapp.home.provider.SessionListProvider
import com.example.collectapp.home.provider.model.SessionCreateModel
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.fragment_session_create_view.*
import kotlinx.android.synthetic.main.fragment_session_create_view.view.*


class SessionCreateView :  BaseFragment<SessionCreateModel>() {

    companion object {
        val TAG = "SessionCreateView"
    }

    override val layoutId: Int = R.layout.fragment_session_create_view
    lateinit var submit : Button
    lateinit var presenter : SessionCreatePresenter

    override fun loadResponse(responseModel: SessionCreateModel) {

        if (responseModel.success) {
            val sessionId = responseModel.data.sessionID
            val sessionToken = responseModel.data.sessionToken
            SharedPref.putLong(Constants.session_ID,sessionId)
            SharedPref.putString(Constants.session_Token,sessionToken!!)
            this.show(responseModel.message)
        }
        else {
            this.show(responseModel.message)
        }
        // To dismiss the fragment
//        dismiss()
    }

    override fun initView() {
        submitSessionCreateBtn.setOnClickListener {
            submitClick()
        }
    }

    private fun submitClick() {
        val jsonObject = JsonObject()
        jsonObject.addProperty("sessionName", sessionNameCreate.sessionNameCreateText.toString())
        val header = SharedPref.getString(Constants.authorization)
        presenter = SessionCreatePresenter(this, SessionListProvider(header!!, jsonObject))
        presenter.getSessionCreateResponse()
    }


}

