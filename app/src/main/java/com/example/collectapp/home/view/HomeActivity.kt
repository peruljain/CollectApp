package com.example.collectapp.home.view

import android.widget.ProgressBar
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.collectapp.R
import com.example.collectapp.base.BaseActivity
import com.example.collectapp.helper.ApiClient
import com.example.collectapp.helper.Constants
import com.example.collectapp.helper.SharedPref
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : BaseActivity(R.layout.activity_home) {

    override lateinit var progressBar: ProgressBar
    override var swipeRefreshLayout: SwipeRefreshLayout? = null
    override var toolbar: Toolbar? = null
    lateinit var navController: NavController
    val rootDestinations = setOf(R.id.sessionListView, R.id.dashboardView)

    override fun initActivity() {
        SharedPref.instantiate(this)
        val accessToken: String? = SharedPref.getString(Constants.authorization)
        var phoneNumber:String? = SharedPref.getString(Constants.phoneNumber)
        ApiClient.instantiateWithAccessToken(this, accessToken)
        progressBar = progressBarHome
        swipeRefreshLayout = swipeRefreshLayoutHome
        navController = findNavController(R.id.fragmentHostHome)
        setupActionBarWithNavController(navController)
        NavigationUI.setupWithNavController(bottomNavHome, navController);
        NavigationUI.setupActionBarWithNavController(this, navController);
    }
}
