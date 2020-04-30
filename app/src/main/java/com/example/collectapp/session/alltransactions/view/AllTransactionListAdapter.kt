package com.example.collectapp.session.alltransactions.view

import com.example.collectapp.R
import com.example.collectapp.base.BaseRecyclerAdapter
import com.example.collectapp.session.alltransactions.provider.model.AllTransactionModel
import kotlinx.android.synthetic.main.item_all_transaction.view.*

class AllTransactionListAdapter() : BaseRecyclerAdapter<AllTransactionModel>(R.layout.item_all_transaction) {
    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        var response = list[position]
        holder.itemView.payerName.text = response.payerName;
        holder.itemView.paidOn.text = response.paidOn.toString();
        holder.itemView.amount.text = response.amount.toString();
        holder.itemView.collectedById.text = response.collectedById.toString();
        holder.itemView.collectedBy.text = response.collectedBy;
        holder.itemView.groupName.text = response.groupName;
        holder.itemView.payerPhoneNumber.text = response.payerPhoneNumber.toString()
    }

}