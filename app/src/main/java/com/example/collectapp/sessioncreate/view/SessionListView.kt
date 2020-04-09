package com.example.collectapp.sessioncreate.view

import android.widget.ProgressBar
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.collectapp.R
import com.example.collectapp.helper.ApiClient
import com.example.collectapp.helper.BaseActivity
import com.example.collectapp.helper.Constants
import com.example.collectapp.helper.SharedPref
import com.example.collectapp.sessioncreate.presenter.SessionListPresenter
import com.example.collectapp.sessioncreate.provider.SessionCreateProvider
import com.example.collectapp.sessioncreate.provider.model.SessionListModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.activity_session_list.*

class SessionListView : BaseActivity(R.layout.activity_session_list){

    override lateinit var progressBar: ProgressBar
    override var swipeRefreshLayout: SwipeRefreshLayout? = null
    override var toolbar: Toolbar? = null

    lateinit var create : FloatingActionButton
    lateinit var join : FloatingActionButton
    lateinit var fm : FragmentManager
    lateinit var createFragment : SessionCreateView
    lateinit var joinFragment : SessionJoinView
    lateinit var presenter : SessionListPresenter

    override fun initActivity() {

        ApiClient.instantiate(this)
        SharedPref.instantiate(this)
        progressBar = progressBarSessionMain
        create = createSession
        join = joinSession
        fm = supportFragmentManager
        createFragment = SessionCreateView()
        joinFragment = SessionJoinView()
        // getList
        getList()
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        sessionRecyclerView.layoutManager = layoutManager

        create.setOnClickListener {
            createClick()
        }
        join.setOnClickListener {
            joinClick()
        }

    }

    fun loadResponse(responseModel: SessionListModel) {

        if (responseModel.success) {
            val adapter= SessionCreateAdapter(this, responseModel.data)
            sessionRecyclerView.adapter = adapter
            this.show(responseModel.message)
        }
        else {
            this.show(responseModel.message)
        }

    }

    private fun getList() {
        var header = SharedPref.getString(Constants.authorization)
        presenter = SessionListPresenter(this, SessionCreateProvider(header!!, JsonObject()))
        presenter.getSessionListResponse()
    }


    fun showProgressBar() {
        progressBar.show()
    }

    fun hideProgressBar() {
        progressBar.hide()
    }


    private fun joinClick() {
    joinFragment.show(fm, "join")
    }

    private fun createClick() {
       createFragment.show(fm,"create")
    }


}