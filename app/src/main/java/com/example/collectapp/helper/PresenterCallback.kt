package com.example.collectapp.helper

interface PresenterCallback<T> {
    fun onSuccess(responseModel: T)
    fun onFailure(message: String)
}