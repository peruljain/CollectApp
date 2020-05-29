package com.example.collectapp

import android.app.Application
import com.example.collectapp.helper.DataFormatter

class CollectApp : Application() {

    override fun onCreate() {
        super.onCreate()
        DataFormatter.createInstance(this)
    }
}