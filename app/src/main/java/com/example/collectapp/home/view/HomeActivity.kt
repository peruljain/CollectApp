package com.example.collectapp.home.view

import android.graphics.Color
import android.widget.ProgressBar
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.collectapp.R
import com.example.collectapp.base.BaseActivity
import com.example.collectapp.dashboard.DashboardView
import com.example.collectapp.helper.ApiClient
import com.example.collectapp.helper.Constants
import com.example.collectapp.helper.SharedPref
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : BaseActivity(R.layout.activity_home) {

    override lateinit var progressBar: ProgressBar
    override var swipeRefreshLayout: SwipeRefreshLayout? = null
    override var toolbar: Toolbar? = null
    lateinit var navController: NavController
    private var activeFragment: Fragment? = null


    override fun initActivity() {
        SharedPref.instantiate(this)
        val accessToken: String? = SharedPref.getString(Constants.AUTHORIZATION)
        var phoneNumber: String? = SharedPref.getString(Constants.PHONE_NUMBER)
        ApiClient.instantiateWithAccessToken(this, accessToken)
        progressBar = progressBarHome
        swipeRefreshLayout = swipeRefreshLayoutHome
        swipeRefreshLayout?.setColorSchemeColors(Color.BLUE, Color.GREEN)
//        navController = findNavController(R.id.fragmentHostHome)
//        setupActionBarWithNavController(navController)
        setUpBottomNavigation()
//        NavigationUI.setupActionBarWithNavController(this, navController)
    }

    private fun setUpBottomNavigation() {
        title = "Active Sessions"
        showSessionList()
        bottomNavHome.run {
            setOnNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.sessionListView -> {
                        swipeRefreshLayout?.isEnabled = true
                        title = "Active Sessions"
                        showSessionList()
                        true
                    }
                    R.id.dashboardView -> {
                        swipeRefreshLayout?.isEnabled = false
                        title = "DashBoard"
                        showDashboard()
                        true
                    }
                    else -> false
                }
            }
        }
    }

    private fun showSessionList() {
        if (activeFragment is SessionListView) return

        val fragmentTransaction = supportFragmentManager.beginTransaction()

        var fragment =
            supportFragmentManager.findFragmentByTag(SessionListView.TAG) as SessionListView?

        if (fragment == null) {
            fragment = SessionListView()
            fragmentTransaction.add(R.id.fragmentHostHome, fragment, SessionListView.TAG)
        } else {
            fragmentTransaction.show(fragment)
        }

        if (activeFragment != null) fragmentTransaction.hide(activeFragment as Fragment)

        fragmentTransaction.commit()

        activeFragment = fragment

        return
    }

    private fun showDashboard() {
        if (activeFragment is DashboardView) return

        val fragmentTransaction = supportFragmentManager.beginTransaction()

        var fragment =
            supportFragmentManager.findFragmentByTag(DashboardView.TAG) as DashboardView?

        if (fragment == null) {
            fragment = DashboardView()
            fragmentTransaction.add(R.id.fragmentHostHome, fragment, DashboardView.TAG)
        } else {
            fragmentTransaction.show(fragment)
        }

        if (activeFragment != null) fragmentTransaction.hide(activeFragment as Fragment)

        fragmentTransaction.commit()

        activeFragment = fragment

        return
    }


}
