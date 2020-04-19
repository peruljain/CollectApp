package com.example.collectapp.session.members.view

import com.example.collectapp.R
import com.example.collectapp.base.BaseRecyclerAdapter
import com.example.collectapp.session.members.provider.model.MemberDataModel
import kotlinx.android.synthetic.main.item_members_list.view.*

class MembersListAdapter() : BaseRecyclerAdapter<MemberDataModel>(R.layout.item_members_list)  {
    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val response = list[position]
        holder.itemView.memberUserName.text = response.userName
        val phoneNumber = "No. ${response.phone.toString()}"
        val joinedOn = "joined ${response.joinedOn.toString()}"
        holder.itemView.memberPhoneNumber.text = phoneNumber
        holder.itemView.memberJoinedDate.text = joinedOn
    }
}