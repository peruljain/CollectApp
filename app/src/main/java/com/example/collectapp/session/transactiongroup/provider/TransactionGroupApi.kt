package com.example.collectapp.session.transactiongroup.provider

import com.example.collectapp.helper.Urls
import com.example.collectapp.session.transactiongroup.provider.model.TransactionCreateModel
import com.example.collectapp.session.transactiongroup.provider.model.TransactionListModel
import com.google.gson.JsonObject
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface TransactionGroupApi {
    @POST(Urls.TRANSACTION_GROUP_CREATE)
    fun getTransactionGroupCreateResponse(@Body jsonObject: JsonObject) : Single<TransactionCreateModel>
    @GET(Urls.TRANSACTION_GROUP_LIST)
    fun getTransactionGroupListResponse(@Query("sessionId") sessionId: Long) : Single<TransactionListModel>
}