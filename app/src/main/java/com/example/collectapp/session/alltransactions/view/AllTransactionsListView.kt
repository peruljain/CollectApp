package com.example.collectapp.session.alltransactions.view

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.collectapp.R
import com.example.collectapp.base.BaseListFragment
import com.example.collectapp.helper.Constants
import com.example.collectapp.session.alltransactions.presenter.AllTransactionListPresenter
import com.example.collectapp.session.alltransactions.provider.model.AllTransactionListModel
import com.example.collectapp.session.alltransactions.provider.model.AllTransactionModel
import kotlinx.android.synthetic.main.fragment_all_transaction_list.*
import timber.log.Timber

class AllTransactionsListView :
    BaseListFragment<AllTransactionListModel, AllTransactionModel, AllTransactionListAdapter>() {

    override val adapter: AllTransactionListAdapter = AllTransactionListAdapter()
    lateinit override var recyclerView: RecyclerView
    override val layoutId: Int = R.layout.fragment_all_transaction_list
    var sessionId: Long? = null
    lateinit var presenter: AllTransactionListPresenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = allTransactionsRecyclerView
        super.onViewCreated(view, savedInstanceState)
    }

    override fun loadResponse(responseModel: AllTransactionListModel) {
        print(responseModel)
        adapter.list = responseModel.data
        adapter.notifyDataSetChanged()
    }

    override fun initView() {
        presenter = AllTransactionListPresenter(this)
        sessionId = requireArguments().getLong(Constants.SESSION_ID)
        getList()
    }

    override fun onDestroyView() {
        presenter.onCleared()
        super.onDestroyView()
    }

    private fun getList() {
        sessionId?.let {
            presenter.getTransactionListResponse(it)
        }
    }

}