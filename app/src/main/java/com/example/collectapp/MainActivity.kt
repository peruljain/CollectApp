package com.example.collectapp

import android.widget.ProgressBar
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.collectapp.helper.ApiClient
import com.example.collectapp.helper.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(R.layout.activity_main) {

    override lateinit  var progressBar: ProgressBar
    override var swipeRefreshLayout: SwipeRefreshLayout? = null
    override var toolbar: Toolbar? = null
    lateinit var navController: NavController


    override fun initActivity() {
        //Instantiate ApiClient instance by passing context of Activity (for caching)
        //using static fun instantiate, requires proper BASE_URL to run
        ApiClient.instantiate(this)
        progressBar = progressBarMain
        swipeRefreshLayout = swipeRefreshLayoutMain
        navController = findNavController(R.id.fragmentHostMain)
    }
}
