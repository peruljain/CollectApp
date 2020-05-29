package com.example.collectapp.dashboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.collectapp.R
import com.example.collectapp.base.BaseActivity

class DashboardView : Fragment() {
    companion object {
        const val TAG = "DashboardView"
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dashboard_view, container, false)
    }


}
