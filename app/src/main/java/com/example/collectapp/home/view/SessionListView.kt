package com.example.collectapp.home.view

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.collectapp.R
import com.example.collectapp.helper.Constants
import com.example.collectapp.helper.SharedPref
import com.example.collectapp.home.presenter.SessionListPresenter
import com.example.collectapp.home.provider.SessionListProvider
import com.example.collectapp.home.provider.model.SessionDataModel
import com.example.collectapp.home.provider.model.SessionListModel
import com.google.gson.JsonObject
import com.taz.cureous.mvp.BaseListFragment
import kotlinx.android.synthetic.main.fragment_session_list.*


class SessionListView :
    BaseListFragment<SessionListModel, SessionDataModel, SessionListAdapter>() {

    lateinit var createSessionFragment: SessionCreateView
    lateinit var joinSessionFragment: SessionJoinView
    lateinit var presenter: SessionListPresenter

    override val adapter: SessionListAdapter = SessionListAdapter()
    lateinit override var recyclerView: RecyclerView
    override val layoutId: Int = R.layout.fragment_session_list

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = sessionRecyclerView
        super.onViewCreated(view, savedInstanceState)
    }

    override fun initView() {
        createSessionFragment = SessionCreateView()
        joinSessionFragment = SessionJoinView()
        // getList
        getList()

        createSessionFAB.setOnClickListener {
            createClick()
        }
        joinSessionFAB.setOnClickListener {
            joinClick()
        }

        adapter.listener = { v, it ->
            show("Clicked: ${it.sessionName}")
        }
    }

    override fun loadResponse(responseModel: SessionListModel) {
        print(responseModel)
        adapter.list = responseModel.data
        adapter.notifyDataSetChanged()
    }

    private fun getList() {
        val header = SharedPref.getString(Constants.authorization)
        presenter = SessionListPresenter(this, SessionListProvider(header!!, JsonObject()))
        presenter.getSessionListResponse()
    }

    private fun joinClick() = joinSessionFragment.show(parentFragmentManager, "JoinSession")

    private fun createClick() = createSessionFragment.show(parentFragmentManager, "CreateSession")


}