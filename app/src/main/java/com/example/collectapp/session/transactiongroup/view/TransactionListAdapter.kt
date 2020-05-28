package com.example.collectapp.session.transactiongroup.view

import com.example.collectapp.R
import com.example.collectapp.base.BaseRecyclerAdapter
import com.example.collectapp.session.transactiongroup.provider.model.TransactionGroupModel
import kotlinx.android.synthetic.main.item_transaction_group.view.*

class TransactionListAdapter : BaseRecyclerAdapter<TransactionGroupModel>(R.layout.item_transaction_group) {
    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val response = list[position]
        holder.itemView.transactionGroupItemName.text = response.groupName
        val createdOnText = "Created on ${response.createdOn}"
//        holder.itemView.transactionGroupId.text = groupId
        holder.itemView.transactionGroupItemCreatedOn.text = createdOnText
    }
}