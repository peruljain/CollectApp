package com.example.collectapp.home.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.collectapp.R
import com.example.collectapp.base.BaseActivity
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

    companion object {
        const val TAG = "SessionListView"
    }

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

        getList()

        requireActivity().sessionNewFAB.setOnClickListener {
            SessionNewHostDialogFragment().show(parentFragmentManager, SessionCreateView.TAG)
        }

        (activity as BaseActivity).swipeRefreshLayout?.setOnRefreshListener {
            (activity as BaseActivity).swipeRefreshLayout?.isRefreshing = false
            getList()
        }

        adapter.listener = { v, it ->
            val intent = Intent(this.context, SessionActivity::class.java)
            intent.putExtra(Constants.SESSION_ID, it.sessionId)
            intent.putExtra(Constants.SESSION_NAME, it.sessionName)
            Timber.d("sessionID = ${it.sessionId}")
            startActivity(intent)
        }
    }

    override fun loadResponse(responseModel: SessionListModel) {
        print(responseModel)
        adapter.list = responseModel.data
        adapter.notifyDataSetChanged()
    }

    private fun getList() {
        val header = SharedPref.getString(Constants.AUTHORIZATION)
        presenter = SessionListPresenter(this, SessionListProvider(header!!, JsonObject()))
        presenter.getSessionListResponse()
    }




}