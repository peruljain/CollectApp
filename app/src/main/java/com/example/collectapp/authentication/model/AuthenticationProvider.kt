package com.example.collectapp.authentication.model

import com.example.collectapp.helper.ApiClient
import com.example.collectapp.helper.PresenterCallback
import com.google.gson.JsonObject
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class AuthenticationProvider(val jsonObject: JsonObject)  {

    // step1 call by presenter
    // step2 send data to presenter

    open fun getUserSignInResponse(callback: PresenterCallback<AuthenticationModel>) {
        ApiClient.retroClient.create(AuthenticationApi ::class.java).getSignInResponse(jsonObject)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy {
                callback.onSuccess(it)
                callback.onFailure(it.message);
            }
    }

    open fun getUserSignUpResponse(callback: PresenterCallback<AuthenticationModel>) {
        ApiClient.retroClient.create(AuthenticationApi ::class.java).getSignUpResponse(jsonObject)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy {
                callback.onSuccess(it)
                callback.onFailure(it.message);
            }
    }

    open fun getUserSignUpOtpResponse(callback: PresenterCallback<AuthenticationModel>) {
        ApiClient.retroClient.create(AuthenticationApi ::class.java).getSignUpOtpResponse(jsonObject)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy {
                callback.onSuccess(it)
                callback.onFailure(it.message);
            }
    }

    open fun getUserForgotPasswordResponse(callback: PresenterCallback<AuthenticationModel>) {
        ApiClient.retroClient.create(AuthenticationApi ::class.java).getForgotPasswordResponse(jsonObject)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy {
                callback.onSuccess(it)
                callback.onFailure(it.message);
            }
    }

    open fun getUserResetPasswordResponse (callback: PresenterCallback<AuthenticationModel>) {
        ApiClient.retroClient.create(AuthenticationApi ::class.java).getForgotPasswordResponse(jsonObject)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy {
                callback.onSuccess(it)
                callback.onFailure(it.message);
            }
    }
}