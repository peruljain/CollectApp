package com.example.collectapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.ProgressBar
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.onNavDestinationSelected
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.collectapp.helper.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(R.layout.activity_main) {

    override lateinit  var progressBar: ProgressBar
    override var swipeRefreshLayout: SwipeRefreshLayout? = null
    override var toolbar: Toolbar? = null
    lateinit var navController: NavController


    override fun initActivity() {
        progressBar = progressBarMain
        swipeRefreshLayout = swipeRefreshLayoutMain
        navController = findNavController(R.id.fragmentHostMain)
    }
}
