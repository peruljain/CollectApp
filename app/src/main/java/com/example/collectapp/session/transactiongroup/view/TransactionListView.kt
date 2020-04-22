package com.example.collectapp.session.transactiongroup.view

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.RecyclerView
import com.example.collectapp.R
import com.example.collectapp.base.BaseListFragment
import com.example.collectapp.helper.Constants
import com.example.collectapp.session.transactiongroup.presenter.TransactionListPresenter
import com.example.collectapp.session.transactiongroup.provider.TransactionGroupProvider
import com.example.collectapp.session.transactiongroup.provider.model.TransactionGroupModel
import com.example.collectapp.session.transactiongroup.provider.model.TransactionListModel
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.fragment_group_transaction_list.*
import kotlinx.android.synthetic.main.fragment_session_list.*

class TransactionListView : BaseListFragment<TransactionListModel, TransactionGroupModel,
        TransactionListAdapter>(){
    override val adapter: TransactionListAdapter = TransactionListAdapter()
    override lateinit var recyclerView: RecyclerView
    override val layoutId: Int = R.layout.fragment_group_transaction_list
    lateinit var presenter: TransactionListPresenter
    lateinit var createTransactionGroupFragment: TransactionGroupCreateView
    override fun loadResponse(responseModel: TransactionListModel) {
        print(responseModel)
        adapter.list = responseModel.data
        adapter.notifyDataSetChanged()
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = transactionGroupRecyclerView
        super.onViewCreated(view, savedInstanceState)
    }
    override fun initView() {
        // getList
        createTransactionGroupFragment = TransactionGroupCreateView()
        createTransactionGroup.setOnClickListener { createTransactionGroupFragment.show(parentFragmentManager,
            arguments!!.getLong(Constants.session_ID).toString()) }
        getList()
    }

    private fun getList() {
        var jsonObject = JsonObject()
        jsonObject.addProperty(Constants.session_ID,arguments!!.getLong(Constants.session_ID))
        presenter = TransactionListPresenter(this, TransactionGroupProvider(jsonObject))
        presenter.getTransactionGroupListResponse()
    }

}