package com.example.collectapp.session.chat.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.collectapp.R
import com.example.collectapp.helper.Constants
import com.example.collectapp.session.chat.pagination.LoadingState
import com.google.firebase.firestore.Query
import kotlinx.android.synthetic.main.fragment_chat.*
import kotlinx.android.synthetic.main.fragment_chat.view.*
import org.joda.time.DateTime
import java.util.*

class ChatView : Fragment() {
    companion object {
        const val TAG = "ChatView"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_chat, container, false)
    }

    lateinit var adapter: ChatAdapter
    lateinit var sessionId: String
    val messagesQuery by lazy {
        sessionCollectionReference
            .orderBy("date", Query.Direction.DESCENDING)
    }

    val newMessagesQuery by lazy {
        messagesQuery.whereAfterDate()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sessionId = requireArguments().getLong(Constants.SESSION_ID).toString()
        activity?.title = requireArguments().getString(Constants.SESSION_NAME).toString()
        initAdapter()
        initRecylerView()
        setUpPageAdapter()
        setupSendMessage()
    }

    private fun initAdapter() {
        val messagesQuery = getChatCollection(sessionId).orderBy("date", Query.Direction.DESCENDING)
        val newMessagesQuery = messagesQuery.whereAfterDate()
        adapter = ChatAdapter(
            paginationQuery = messagesQuery,
            realTimeQuery = newMessagesQuery,
            lifecycleOwner = this
        )
    }

    private fun initRecylerView() {
        messageListRecyclerView.apply {
            hasFixedSize()
            layoutManager = LinearLayoutManager(context)//, RecyclerView.VERTICAL, true)
        }
        messageListRecyclerView.adapter = adapter
    }


    fun setUpPageAdapter() {
        adapter.loadingState.observe(requireActivity(), Observer {
            if (it != null) {
                handleLoadingState(it)
            }
        })
    }

    private fun handleLoadingState(loadingState: LoadingState) {
        when (loadingState) {
            LoadingState.EMPTY -> renderView(empty = true)
            LoadingState.LOADING_INITIAL -> renderView(loading = true)
            LoadingState.INITIAL_LOADED -> renderView(messages = true)
            LoadingState.LOADING_MORE -> renderView(loading = true, messages = true)
            LoadingState.MORE_LOADED -> renderView(messages = true)
            LoadingState.FINISHED -> renderView(messages = true)
            LoadingState.ERROR -> renderView(error = true)
            LoadingState.NEW_ITEM -> renderView(messages = true, scroll = true)
            LoadingState.DELETED_ITEM -> {
            }
        }
    }

    private fun setupSendMessage() {
        messgaeInputTextLayout.setEndIconOnClickListener {
            val message = messgaeInputTextLayout.messageInputText.text.toString()
            if (message.isEmpty())
                return@setEndIconOnClickListener

            val id = UUID.randomUUID().toString()
            getChatCollection(sessionId)
                .document(id)
                .set(
                    ChatMessage(
                        id = UUID.randomUUID().toString(),
                        text = message,
                        date = DateTime.now().millis,
                        userId = 5,
                        name = "Anurag"
                    )
                )
            messgaeInputTextLayout.messageInputText.setText("")
        }
    }

    private fun renderView(
        empty: Boolean = false,
        loading: Boolean = false,
        error: Boolean = false,
        messages: Boolean = false,
        scroll: Boolean = false
    ) {
        errorText.toggleVisibility(error)
        progressBar.toggleVisibility(loading)
        emptyState.toggleVisibility(empty)
        messageListRecyclerView.toggleVisibility(messages)

        if (scroll)
            messageListRecyclerView.smoothScrollToPosition(adapter.itemCount - 1)
    }

    private fun View.toggleVisibility(show: Boolean) {
        visibility = if (show) View.VISIBLE else View.GONE
    }

    infix fun show(message: String) = Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()

}
