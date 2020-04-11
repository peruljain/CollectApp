package com.example.collectapp.home.provider

import com.example.collectapp.helper.ApiClient
import com.example.collectapp.helper.GeneralModel
import com.example.collectapp.helper.PresenterCallback
import com.example.collectapp.home.provider.model.SessionCreateModel
import com.example.collectapp.home.provider.model.SessionListModel
import com.google.gson.JsonObject
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class SessionListProvider(var header: String, val jsonObject: JsonObject) {

    // step1 call by presenter
    // step2 send data to presenter

    open fun getUserSessionCreateResponse(callback: PresenterCallback<SessionCreateModel>) {
        ApiClient.retroClientCache.create(SessionApi::class.java)
            .getSessionCreateResponse(header, jsonObject)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy {
                callback.onSuccess(it)
                callback.onFailure(it.message);
            }
    }

    open fun getUserSessionJoinResponse(callback: PresenterCallback<GeneralModel>) {
        ApiClient.retroClientCache.create(SessionApi::class.java)
            .getSessionJoinResponse(header, jsonObject)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy {
                callback.onSuccess(it)
                callback.onFailure(it.message);
            }
    }

    open fun getUserSessionListResponse(callback: PresenterCallback<SessionListModel>) {
        ApiClient.retroClientCache.create(SessionApi::class.java).getSessionListResponse()
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