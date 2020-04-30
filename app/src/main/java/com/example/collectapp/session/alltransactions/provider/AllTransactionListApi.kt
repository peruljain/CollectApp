package com.example.collectapp.session.alltransactions.provider

import com.example.collectapp.helper.Urls
import com.example.collectapp.session.alltransactions.provider.model.AllTransactionListModel
import com.google.gson.JsonObject
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET

interface AllTransactionListApi {
    @GET(Urls.ALL_TRANSACTION_LIST)
    fun getTransactionListResponse(@Body jsonObject: JsonObject) : Single<AllTransactionListModel>
}