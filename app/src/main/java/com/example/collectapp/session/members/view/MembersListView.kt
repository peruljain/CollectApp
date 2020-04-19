package com.example.collectapp.session.members.view

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.collectapp.R
import com.example.collectapp.base.BaseListFragment
import com.example.collectapp.base.BaseRecyclerAdapter
import com.example.collectapp.helper.Constants
import com.example.collectapp.session.members.presenter.MemberListPresenter
import com.example.collectapp.session.members.view.MembersListAdapter
import com.example.collectapp.session.members.provider.SessionMemberProvider
import com.example.collectapp.session.members.provider.model.MemberDataModel
import com.example.collectapp.session.members.provider.model.MembersListModel
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.fragment_session_list.*

class MembersListView : BaseListFragment<MembersListModel, MemberDataModel, BaseRecyclerAdapter<MemberDataModel>>() {

    override val adapter: BaseRecyclerAdapter<MemberDataModel> =
        MembersListAdapter()

    lateinit override  var recyclerView: RecyclerView
    override val layoutId: Int = R.layout.fragment_member_list
    lateinit var presenter : MemberListPresenter
     var sessionId : Long? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = sessionRecyclerView
        super.onViewCreated(view, savedInstanceState)
    }

    override fun loadResponse(responseModel: MembersListModel) {
        print(responseModel)
        adapter.list = responseModel.data
        adapter.notifyDataSetChanged()
    }

    override fun initView() {
        sessionId = arguments!!.getLong(Constants.session_ID)
        getList()
    }

    private fun getList() {
        var jsonObject = JsonObject()
        jsonObject.addProperty("sessionId", sessionId)
        presenter =
            MemberListPresenter(
                this,
                SessionMemberProvider(
                    jsonObject
                )
            )
        presenter.getMemberListResponse()
    }
}