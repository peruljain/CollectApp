package com.example.collectapp.session

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.collectapp.R
import com.example.collectapp.base.BaseFragment
import com.example.collectapp.helper.Constants
import com.example.collectapp.helper.GeneralModel
import kotlinx.android.synthetic.main.fragment_session_view.*

class SessionViewFragment : BaseFragment<GeneralModel>() {
    override val layoutId: Int = R.layout.fragment_session_view;
    var navController : NavController? = null;
    override fun loadResponse(responseModel: GeneralModel) {
    }
    override fun initView() {
        navController = view!!.findNavController()
        var sessionId = activity?.intent!!.getLongExtra(Constants.session_ID,0)
        var bundle = Bundle()
        bundle.putLong(Constants.session_ID,sessionId)
        sessionMembersCard.setOnClickListener { navController!!.navigate(R.id.action_sessionViewFragment_to_membersListView,bundle)}
        sessionTransactionGroup.setOnClickListener { navController!!.navigate(R.id.action_sessionViewFragment_to_transactionListView,bundle)}
        sessionTransactionCard.setOnClickListener { navController!!.navigate(R.id.action_sessionViewFragment_to_allTransactionsListView,bundle)}
    }

}
