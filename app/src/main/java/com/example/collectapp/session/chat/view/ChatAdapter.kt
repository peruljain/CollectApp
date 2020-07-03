package com.example.collectapp.session.chat.view

import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SortedList
import androidx.recyclerview.widget.SortedListAdapterCallback
import com.example.collectapp.R
import com.example.collectapp.helper.DataFormatter
import com.example.collectapp.session.chat.pagination.FirestoreRealTimePaginationAdapter
import com.google.firebase.firestore.Query
import kotlinx.android.synthetic.main.item_chat_message.view.*


class ChatAdapter(
    lifecycleOwner: LifecycleOwner?,
    paginationQuery : Query,
    realTimeQuery : Query
) : FirestoreRealTimePaginationAdapter<ChatMessage, ChatAdapter.RecyclerViewHolder>(
    paginationQuery = paginationQuery,
    realTimeQuery = realTimeQuery,
    prefetchDistance = 3,
    pageSize = 10,
    parser = { documentSnapshot ->
        documentSnapshot.toObject(ChatMessage::class.java)
    },
    lifecycleOwner = lifecycleOwner
) {

    override val data: SortedList<ChatMessage> = SortedList<ChatMessage>(
        ChatMessage::class.java,
        object : SortedListAdapterCallback<ChatMessage>(this) {
            override fun compare(a: ChatMessage, b: ChatMessage): Int =
                a.date.compareTo(b.date)

            override fun areContentsTheSame(oldItem: ChatMessage, newItem: ChatMessage): Boolean =
                oldItem.text == newItem.text

            override fun areItemsTheSame(item1: ChatMessage, item2: ChatMessage): Boolean =
                item1.id == item2.id
        })

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): RecyclerViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_chat_message, parent, false)
        return RecyclerViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: RecyclerViewHolder,
        position: Int
    ) {
        super.onBindViewHolder(holder, position)
        holder.itemView.personNameTextView.text = data[position].name
        holder.itemView.dateTextView.text =
            DataFormatter.getInstance().getDurationBasedFormattedDate(data[position].date)
        holder.itemView.messageTextView.text = data[position].text
        if (data[position].userId == 5.toLong()) {
            holder.itemView.cardChildLayout.setBackgroundResource(R.drawable.message_mine_background)
            holder.itemView.cardContainer.gravity = Gravity.END
            holder.itemView.avatarImage.visibility = View.GONE
        } else {
            holder.itemView.cardChildLayout.setBackgroundResource(R.drawable.message_theirs_background)
            holder.itemView.cardContainer.gravity = Gravity.START
            holder.itemView.avatarImage.visibility = View.VISIBLE
            holder.itemView.avatarImage.bind(data[position].name,"")
        }
    }

    open class RecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}

}

data class ChatMessage(
    val id: String = "",
    val name: String = "",
    val userId: Long = -1,
    val text: String = "",
    val date: Long = -1
)
