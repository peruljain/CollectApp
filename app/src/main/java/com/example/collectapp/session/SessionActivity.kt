package com.example.collectapp.session

import android.os.Bundle
import android.widget.ProgressBar
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.collectapp.R
import com.example.collectapp.base.BaseActivity
import com.example.collectapp.helper.ApiClient
import com.example.collectapp.helper.Constants
import com.example.collectapp.helper.SharedPref
import kotlinx.android.synthetic.main.activity_session.*

class SessionActivity : BaseActivity(R.layout.activity_session) {

    override lateinit  var progressBar: ProgressBar
    override var swipeRefreshLayout: SwipeRefreshLayout? = null
    override var toolbar: Toolbar? = null
    private lateinit var navController: NavController

    override fun initActivity() {
        SharedPref.instantiate(this)
        val accessToken: String? = SharedPref.getString(Constants.AUTHORIZATION)
        ApiClient.instantiateWithAccessToken(this, accessToken)
        progressBar = progressBarSession
        swipeRefreshLayout = swipeRefreshLayoutSession
        val sessionId = intent.getLongExtra(Constants.SESSION_ID,0)
        navController = findNavController(R.id.fragmentHostSession)
        val bundle = Bundle()
        bundle.putLong(Constants.SESSION_ID, sessionId)
//        navController.navigate(R.id.fragmentHostSession,bundle)
        setupActionBarWithNavController(navController)
        supportActionBar?.setHomeButtonEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean = navController.navigateUp()
}