package com.example.collectapp.authentication.model

import android.util.Log
import com.example.collectapp.helper.ApiClient
import com.example.collectapp.helper.PresenterCallback
import com.google.gson.JsonObject
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class AuthenticationProvider(val jsonObject: JsonObject)  {

    // step1 call by presenter
    // step2 send data to presenter


     fun getUserSignInResponse(callback: PresenterCallback<AuthenticationModel>): Disposable {

        return ApiClient.retroClient.create(AuthenticationApi ::class.java).getSignInResponse(jsonObject)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    if(it.success)      callback.onSuccess(it)
                    else    callback.onFailure("Error: ${it.message}")
                },
                onError = { callback.onFailure(it.message?:"Some Error Occurred") }
            )
    }

     fun getUserSignUpResponse(callback: PresenterCallback<AuthenticationModel>): Disposable {
         return ApiClient.retroClient.create(AuthenticationApi ::class.java).getSignUpResponse(jsonObject)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError { t-> Log.e("Error", t.message.toString()) }
            .subscribeBy(
                onSuccess = {
                    if(it.success)      callback.onSuccess(it)
                    else    callback.onFailure("Error: ${it.message}")
                },
                onError = { callback.onFailure(it.message?:"Some Error Occurred") }
            )
    }

     fun getUserSignUpOtpResponse(callback: PresenterCallback<AuthenticationModel>): Disposable {
         return ApiClient.retroClient.create(AuthenticationApi ::class.java).getSignUpOtpResponse(jsonObject)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    if(it.success)      callback.onSuccess(it)
                    else    callback.onFailure("Error: ${it.message}")
                },
                onError = { callback.onFailure(it.message?:"Some Error Occurred") }
            )
    }

     fun getUserForgotPasswordResponse(callback: PresenterCallback<AuthenticationModel>): Disposable {
         return ApiClient.retroClient.create(AuthenticationApi ::class.java).getForgotPasswordResponse(jsonObject)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    if(it.success)      callback.onSuccess(it)
                    else    callback.onFailure("Error: ${it.message}")
                },
                onError = { callback.onFailure(it.message?:"Some Error Occurred") }
            )
    }

     fun getUserResetPasswordResponse (callback: PresenterCallback<AuthenticationModel>): Disposable {
         return ApiClient.retroClient.create(AuthenticationApi ::class.java).getResetPasswordResponse(jsonObject)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    if(it.success)      callback.onSuccess(it)
                    else    callback.onFailure("Error: ${it.message}")
                },
                onError = { callback.onFailure(it.message?:"Some Error Occurred") }
            )
    }
}