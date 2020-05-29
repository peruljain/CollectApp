package com.example.collectapp.session.transactiongroup.view

import android.os.Bundle
import android.view.View
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
        presenter = TransactionListPresenter(this, TransactionGroupProvider())
        createTransactionGroupFragment = TransactionGroupCreateView()
        createTransactionGroup.setOnClickListener { createTransactionGroupFragment.show(parentFragmentManager,
            requireArguments().getLong(Constants.SESSION_ID).toString()) }
        getList()
    }

    private fun getList() {
        presenter.getTransactionGroupListResponse(requireArguments().getLong(Constants.SESSION_ID))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.onCleared()
    }
}
