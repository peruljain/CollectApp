package com.example.collectapp.session

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.collectapp.R
import com.example.collectapp.base.BaseFragment
import com.example.collectapp.helper.Constants
import com.example.collectapp.helper.GeneralModel
import kotlinx.android.synthetic.main.fragment_session_view.*
import timber.log.Timber

class SessionViewFragment : BaseFragment<GeneralModel>() {
    override val layoutId: Int = R.layout.fragment_session_view
    var navController : NavController? = null
    override fun loadResponse(responseModel: GeneralModel) {
    }
    override fun initView() {
        navController = requireView().findNavController()
        val sessionId = activity?.intent!!.getLongExtra(Constants.session_ID,0)
        val bundle = Bundle()
        bundle.putLong(Constants.session_ID,sessionId)
        Timber.d("sessionId= $sessionId")
        sessionMembersCard.setOnClickListener { navController!!.navigate(R.id.action_sessionViewFragment_to_membersListView,bundle)}
        sessionTransactionGroup.setOnClickListener { navController!!.navigate(R.id.action_sessionViewFragment_to_transactionListView,bundle)}
        sessionTransactionCard.setOnClickListener { navController!!.navigate(R.id.action_sessionViewFragment_to_allTransactionsListView,bundle)}
    }

}
