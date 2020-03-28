package com.example.collectapp.helper

abstract class BaseProvider<T>() {
    abstract fun getProviderResponse(callback: PresenterCallback<T>)
}