package com.example.collectapp.home.view

import android.graphics.drawable.GradientDrawable
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.FragmentManager
import com.example.collectapp.base.BaseDialogFragment
import com.example.collectapp.helper.GeneralModel
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.collectapp.R
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class SessionNewHostDialogFragment   : BaseDialogFragment<GeneralModel>() {
    companion object {
        val TAB_TITLES = arrayOf("Create", "Join")
    }
    override val layoutId: Int = com.example.collectapp.R.layout.fragment_dialog_session_new

    override fun loadResponse(responseModel: GeneralModel) {

    }
    override fun initView() {
        val tabLayout: TabLayout = requireView().findViewById(R.id.tabView)
        val viewPager : ViewPager2 = requireView().findViewById(R.id.view_pager)
        viewPager.adapter = SessionNewTabsAdapter(childFragmentManager, lifecycle)
        viewPager.isUserInputEnabled = true
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = TAB_TITLES[position]
        }.attach()
        tabLayout.changeTabsFont()

    }

    private fun TabLayout.changeTabsFont() {
        val vg = this.getChildAt(0) as ViewGroup
        val tabsCount = vg.childCount
        for (j in 0 until tabsCount) {
            val vgTab = vg.getChildAt(j) as ViewGroup
            val tabChildCount = vgTab.childCount
            for (i in 0 until tabChildCount) {
                val tabViewChild = vgTab.getChildAt(i)
                if (tabViewChild is TextView) {
                    tabViewChild.letterSpacing = 0.1f
                    val tf = ResourcesCompat.getFont(requireContext(),R.font.ebrima)
                    tabViewChild.setTypeface(tf)
                }
            }
        }
    }
}

class SessionNewTabsAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return SessionCreateView()
            else -> return SessionJoinView()
        }
    }
}