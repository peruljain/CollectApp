package com.example.collectapp.session.members.provider

import com.example.collectapp.helper.ApiClient
import com.example.collectapp.helper.PresenterCallback
import com.example.collectapp.session.members.provider.model.MembersListModel
import com.google.gson.JsonObject
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import org.json.JSONObject

class SessionMemberProvider (var jsonObject: JsonObject){

    open fun getUserMembersListResponse(callback: PresenterCallback<MembersListModel>) {

        ApiClient.retroClientCache.create(SessionMemberApi::class.java)
            .getMemberList(jsonObject)
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