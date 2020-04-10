package com.example.collectapp.base

import com.example.collectapp.helper.PresenterCallback

abstract class BaseProvider<T>() {
    abstract fun getProviderResponse(callback: PresenterCallback<T>)
}