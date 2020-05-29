package com.example.collectapp.session.alltransactions.view

import android.content.Context
import com.example.collectapp.R
import com.example.collectapp.base.BaseRecyclerAdapter
import com.example.collectapp.helper.DataFormatter
import com.example.collectapp.session.alltransactions.provider.model.AllTransactionModel
import kotlinx.android.synthetic.main.item_all_transaction.view.*
import java.text.NumberFormat
import java.util.*

class AllTransactionListAdapter() : BaseRecyclerAdapter<AllTransactionModel>(R.layout.item_all_transaction) {
    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val response = list[position]
        holder.itemView.payerName.text = response.payerName
        holder.itemView.paidOn.text = response.paidOn
        holder.itemView.amount.text = DataFormatter.getInstance().renderMoney(response.amount.toDouble())
//        holder.itemView.collectedById.text = response.collectedById.toString()
        holder.itemView.recievedByText.text = response.collectedBy
        holder.itemView.groupNameText.text = response.groupName
        holder.itemView.callText.text = response.payerPhoneNumber.toString()
    }


}