package com.example.collectapp.home.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.collectapp.R
import com.example.collectapp.base.BaseListFragment
import com.example.collectapp.helper.Constants
import com.example.collectapp.helper.SharedPref
import com.example.collectapp.home.presenter.SessionListPresenter
import com.example.collectapp.home.provider.SessionListProvider
import com.example.collectapp.home.provider.model.SessionDataModel
import com.example.collectapp.home.provider.model.SessionListModel
import com.example.collectapp.session.SessionActivity
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.fragment_session_list.*
import timber.log.Timber


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

        requireActivity().sessionNewFAB.setOnClickListener {
            SessionNewHostDialogFragment().show(parentFragmentManager, SessionCreateView.TAG)
        }


        adapter.listener = { v, it ->
            val intent = Intent(this.context, SessionActivity::class.java)
            intent.putExtra(Constants.session_ID, it.sessionId)
            Timber.d("sessionID = ${it.sessionId}")
            startActivity(intent)
            show("Clicked: ${it.sessionId}")
        }
    }

    override fun loadResponse(responseModel: SessionListModel) {
        print(responseModel)
//        adapter.list = responseModel.data
        adapter.list = List(10){responseModel.data[0]}
        adapter.notifyDataSetChanged()
    }

    private fun getList() {
        val header = SharedPref.getString(Constants.authorization)
        presenter = SessionListPresenter(this, SessionListProvider(header!!, JsonObject()))
        presenter.getSessionListResponse()
    }




}