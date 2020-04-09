package com.example.collectapp.sessioncreate.provider

import com.example.collectapp.helper.ApiClient
import com.example.collectapp.helper.GeneralModel
import com.example.collectapp.helper.PresenterCallback
import com.example.collectapp.sessioncreate.provider.model.SessionCreateModel
import com.example.collectapp.sessioncreate.provider.model.SessionListModel
import com.google.gson.JsonObject
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class SessionCreateProvider (var header : String, val jsonObject: JsonObject) {

    // step1 call by presenter
    // step2 send data to presenter

    open fun getUserSessionCreateResponse(callback: PresenterCallback<SessionCreateModel>) {
        ApiClient.retroClient.create(SessionApi::class.java).getSessionCreateResponse(header, jsonObject)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy {
                callback.onSuccess(it)
                callback.onFailure(it.message);
            }
    }

    open fun getUserSessionJoinResponse(callback: PresenterCallback<GeneralModel>) {
        ApiClient.retroClient.create(SessionApi::class.java).getSessionJoinResponse(header, jsonObject)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy {
                callback.onSuccess(it)
                callback.onFailure(it.message);
            }
    }

    open fun getUserSessionListResponse(callback: PresenterCallback<SessionListModel>) {
        ApiClient.retroClient.create(SessionApi::class.java).getSessionListResponse(header)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy {
                callback.onSuccess(it)
                callback.onFailure(it.message);
            }
    }

}