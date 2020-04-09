package com.example.collectapp.sessioncreate.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.collectapp.R
import com.example.collectapp.sessioncreate.provider.model.SessionDataModel
import kotlinx.android.synthetic.main.session_create_rv.view.*

class SessionCreateAdapter (val context : Context,val data : List<SessionDataModel>) :  RecyclerView.Adapter<SessionCreateAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.session_create_rv, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val response = data[position]
        holder.setData(response,position)

    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var sessionData : SessionDataModel ? = null
        var currentPosition :Int = 0

        init {

            itemView.setOnClickListener {
                //Todo : Move to new activity
                Toast.makeText(context, sessionData!!.sessionName + " Clicked !", Toast.LENGTH_LONG).show()
            }

        }

        fun setData(response: SessionDataModel, position: Int) {
            itemView.sessionListName.text = response.sessionName
            itemView.sessionCreatedBy.text = response.createdBy
            this.currentPosition = position
            this.sessionData = response
        }
    }
}