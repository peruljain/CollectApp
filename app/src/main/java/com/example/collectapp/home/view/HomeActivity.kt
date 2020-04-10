package com.example.collectapp.home.view

import android.widget.ProgressBar
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.collectapp.R
import com.example.collectapp.base.BaseActivity
import com.example.collectapp.helper.*
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : BaseActivity(R.layout.activity_home) {

    override lateinit  var progressBar: ProgressBar
    override var swipeRefreshLayout: SwipeRefreshLayout? = null
    override var toolbar: Toolbar? = null
    lateinit var navController: NavController


    override fun initActivity() {

        SharedPref.instantiate(this)
        val accessToken:String? = SharedPref.getString(Constants.authorization)
//        var phoneNumber:String? = SharedPref.getString(Constants.phoneNumber)
        ApiClient.instantiateWithAccessToken(this,accessToken)
        progressBar = progressBarHome
        swipeRefreshLayout = swipeRefreshLayoutHome
        navController = findNavController(R.id.fragmentHostHome)
        NavigationUI.setupWithNavController(bottomNavHome, navController);
        NavigationUI.setupActionBarWithNavController(this, navController);
    }
}
