package com.example.collectapp.dashboard

import android.R.attr.x
import android.R.attr.y
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.collectapp.R
import kotlinx.android.synthetic.main.fragment_dashboard_view.*
import me.fahmisdk6.avatarview.AvatarView


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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
         userImageView.bind("Tazril Parveez Ali", "")
    }


}
