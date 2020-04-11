package com.example.collectapp.home.view

import com.example.collectapp.R
import com.example.collectapp.home.provider.model.SessionDataModel
import com.taz.cureous.mvp.BaseRecyclerAdapter
import kotlinx.android.synthetic.main.item_session_list.view.*

class SessionListAdapter ()
    :  BaseRecyclerAdapter<SessionDataModel>(R.layout.item_session_list) {

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val response = list[position]
        holder.itemView.sessionListItemName.text = response.sessionName
        val createdByText = "Created by ${response.createdBy}"
        val createdOnText = "on ${response.createdOn}"
        holder.itemView.sessionListItemCreatedBy.text = createdByText
        holder.itemView.sessionListItemCreatedOn.text = createdOnText
    }

}