package com.example.collectapp.session.transactiongroup.provider

import com.example.collectapp.helper.ApiClient
import com.example.collectapp.helper.PresenterCallback
import com.example.collectapp.session.transactiongroup.provider.model.TransactionCreateModel
import com.example.collectapp.session.transactiongroup.provider.model.TransactionListModel
import com.google.gson.JsonObject
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class TransactionGroupProvider(var jsonObject: JsonObject){
    open fun getUserCreateGroupResponse(callback: PresenterCallback<TransactionCreateModel>){
        ApiClient.retroClientCache.create(TransactionGroupApi::class.java)
            .getTransactionGroupCreateResponse(jsonObject)
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
    open fun getUserListGroupResponse(callback: PresenterCallback<TransactionListModel>){
        ApiClient.retroClientCache.create(TransactionGroupApi::class.java)
            .getTransactionGroupListResponse(jsonObject)
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