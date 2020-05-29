package com.example.collectapp.session.alltransactions.provider

import com.example.collectapp.base.BaseProvider
import com.example.collectapp.helper.ApiClient
import com.example.collectapp.helper.PresenterCallback
import com.example.collectapp.session.alltransactions.provider.model.AllTransactionListModel
import com.google.gson.JsonObject
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class AllTransactionProvider() : BaseProvider<AllTransactionListModel>(){

    fun getAllTransactionListResponse(sessionId: Long,callback: PresenterCallback<AllTransactionListModel>): Disposable {

       return ApiClient.retroClientCache.create(AllTransactionListApi::class.java)
           .getTransactionListResponse(sessionId)
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