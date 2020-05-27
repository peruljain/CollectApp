package com.example.collectapp.home.view

import android.widget.Button
import android.widget.EditText

import com.example.collectapp.R
import com.example.collectapp.base.BaseDialogFragment
import com.example.collectapp.base.BaseFragment
import com.example.collectapp.helper.Constants
import com.example.collectapp.helper.GeneralModel
import com.example.collectapp.helper.SharedPref
import com.example.collectapp.home.presenter.SessionJoinPresenter
import com.example.collectapp.home.provider.SessionListProvider
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.fragment_session_join_view.*
import kotlinx.android.synthetic.main.fragment_session_join_view.view.*


class SessionJoinView : BaseFragment<GeneralModel>() {
    companion object {
        val TAG = "SessionJoinView"
    }
    override val layoutId: Int = R.layout.fragment_session_join_view

    lateinit var sessionToken : EditText
    lateinit var submit : Button
    lateinit var cancel : Button
    lateinit var presenter : SessionJoinPresenter

    override fun loadResponse(responseModel: GeneralModel) {

        if(responseModel.success) {
            this.show(responseModel.message)

//            var intent = Intent(this.context, SessionListView::class.java)
//            startActivity(intent)
        }
        else {
            this.show(responseModel.message)
        }
//        dismiss()

    }

    override fun initView() {
        sessionToken = joinSessionToken.joinSessionTokenText
        submit = submitJoinButton
        submit.setOnClickListener {
            submitClick()
        }
    }


    private fun submitClick() {
        val jsonObject = JsonObject()
        jsonObject.addProperty(Constants.session_Token, sessionToken.toString())
        jsonObject.addProperty(Constants.session_ID,SharedPref.getLong(Constants.session_ID))
        val header = SharedPref.getString(Constants.authorization)
        presenter = SessionJoinPresenter(this, SessionListProvider(header!!, jsonObject))
        presenter.getJoinReponse()
    }

}
