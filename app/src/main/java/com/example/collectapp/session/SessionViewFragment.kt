package com.example.collectapp.session

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context.CLIPBOARD_SERVICE
import android.content.Intent
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.collectapp.R
import com.example.collectapp.base.BaseFragment
import com.example.collectapp.helper.Constants
import com.example.collectapp.helper.GeneralModel
import kotlinx.android.synthetic.main.fragment_session_view.*

class SessionViewFragment : BaseFragment<GeneralModel>() {
    override val layoutId: Int = R.layout.fragment_session_view
    var navController: NavController? = null

    override fun loadResponse(responseModel: GeneralModel) {

    }

    override fun initView() {
        navController = requireView().findNavController()
        activity?.title =  activity?.intent?.getStringExtra(Constants.SESSION_NAME)?:"Session Detail"
        configureTokenView()
        configureListeners()
    }


    private fun configureTokenView() {
        val token = getString(R.string.sample_token)
        action_copy.setOnClickListener {
            val clipboard = activity?.getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText(Constants.SESSION_TOKEN, token)
            clipboard.setPrimaryClip(clip)
        }
        action_share.setOnClickListener {

            val message = "Join Collect App Session using $token"
            val sendInviteIntent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, message)
                type = "text/plain"
            }
            startActivity(Intent.createChooser(sendInviteIntent, "Invite a friend via..."))
        }
    }


    private fun configureListeners() {
        val sessionId = activity?.intent?.getLongExtra(Constants.SESSION_ID, 0) ?: 0
        val bundle = Bundle()
        bundle.putLong(Constants.SESSION_ID, sessionId)
        sessionMembersCard.setOnClickListener {
            navController!!.navigate(
                R.id.action_sessionViewFragment_to_membersListView,
                bundle
            )
        }
        sessionTransactionGroup.setOnClickListener {
            navController!!.navigate(
                R.id.action_sessionViewFragment_to_transactionListView,
                bundle
            )
        }
        sessionTransactionCard.setOnClickListener {
            navController!!.navigate(
                R.id.action_sessionViewFragment_to_allTransactionsListView,
                bundle
            )
        }
    }
}
