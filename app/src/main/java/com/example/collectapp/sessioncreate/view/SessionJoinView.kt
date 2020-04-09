package com.example.collectapp.sessioncreate.view

import android.content.Intent
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.DialogFragment

import com.example.collectapp.R
import com.example.collectapp.helper.BaseDialogFragment
import com.example.collectapp.helper.Constants
import com.example.collectapp.helper.GeneralModel
import com.example.collectapp.helper.SharedPref
import com.example.collectapp.sessioncreate.presenter.SessionJoinPresenter
import com.example.collectapp.sessioncreate.provider.SessionCreateProvider
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.fragment_session_join_view.*


class SessionJoinView : BaseDialogFragment<GeneralModel>() {

    override val layoutId: Int = R.layout.fragment_session_join_view

    lateinit var sessionToken : EditText
    lateinit var submit : Button
    lateinit var cancel : Button
    lateinit var presenter : SessionJoinPresenter

    override fun loadResponse(responseModel: GeneralModel) {

        if(responseModel.success) {
            this.show(responseModel.message)
            var intent = Intent(this.context, SessionListView::class.java)
            startActivity(intent)
        }
        else {
            this.show(responseModel.message)
        }

    }

    override fun initView() {
        sessionToken = joinSessionToken
        submit = submitJoinButton
        cancel = cancelJoinButton
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
        jsonObject.addProperty(Constants.session_Token, SharedPref.getString(Constants.session_Token))
        jsonObject.addProperty(Constants.session_ID,SharedPref.getLong(Constants.session_ID))
        var header = SharedPref.getString(Constants.authorization)
        presenter = SessionJoinPresenter(this, SessionCreateProvider(header!!, jsonObject))
        presenter.getJoinReponse()
    }

}
