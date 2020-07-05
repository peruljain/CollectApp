package com.example.collectapp.home.view

import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.DialogFragment

import com.example.collectapp.R
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
    var presenter : SessionJoinPresenter? = null

    override fun loadResponse(responseModel: GeneralModel) {

        if(responseModel.success) {
            this.show(responseModel.message)
            (parentFragment as SessionNewHostDialogFragment).run{
//                refreshList()
                dismiss()
            }
//            var intent = Intent(this.context, SessionListView::class.java)
//            startActivity(intent)
        }
        else {
            this.show(responseModel.message)
        }

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
        jsonObject.addProperty(Constants.SESSION_TOKEN, sessionToken.text.toString())
        jsonObject.addProperty(Constants.SESSION_ID,SharedPref.getLong(Constants.SESSION_ID))
        val header = SharedPref.getString(Constants.AUTHORIZATION)
        presenter = SessionJoinPresenter(this, SessionListProvider(header!!, jsonObject))
        presenter?.getJoinReponse()
    }

    override fun onDestroyView() {
        presenter?.onCleared()
        super.onDestroyView()
    }
}
