package com.example.collectapp.session.transactiongroup.view

import android.util.Log
import androidx.core.os.bundleOf
import com.example.collectapp.R
import com.example.collectapp.base.BaseDialogFragment
import com.example.collectapp.helper.Constants
import com.example.collectapp.session.transactiongroup.presenter.TransactionGroupCreatePresenter
import com.example.collectapp.session.transactiongroup.provider.TransactionGroupProvider
import com.example.collectapp.session.transactiongroup.provider.model.TransactionCreateModel
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.fragment_transaction_group_create_view.*
import kotlinx.android.synthetic.main.fragment_transaction_group_create_view.view.*

class TransactionGroupCreateView : BaseDialogFragment<TransactionCreateModel>() {
    override val layoutId: Int = R.layout.fragment_transaction_group_create_view
    lateinit var presenter : TransactionGroupCreatePresenter

    override fun loadResponse(responseModel: TransactionCreateModel) {
        if (responseModel.success) {
            this.show(responseModel.message)
        }
        else {
            this.show(responseModel.message)
        }
        // To dismiss the fragment
        dismiss()
    }

    override fun initView() {

        presenter = TransactionGroupCreatePresenter(this)
        submitTransactionGroupCreateBtn.setOnClickListener {
            submitClick()
        }
        cancelButtonResponse.setOnClickListener {
            dismiss()
        }
    }

    private fun submitClick() {
        val jsonObject = JsonObject()
        jsonObject.addProperty("groupName", transactionGroupNameCreate.transactionGroupNameCreateText.toString())
        var sessionId = arguments?.getLong(Constants.SESSION_ID)
        jsonObject.addProperty("sessionId", sessionId)
        presenter.getTransactionGroupCreateResponse(jsonObject)
    }


}
