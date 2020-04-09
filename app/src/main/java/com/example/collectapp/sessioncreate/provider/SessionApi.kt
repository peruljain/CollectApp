package com.example.collectapp.sessioncreate.provider

import com.example.collectapp.helper.Constants
import com.example.collectapp.helper.GeneralModel
import com.example.collectapp.helper.SharedPref
import com.example.collectapp.helper.Urls
import com.example.collectapp.sessioncreate.provider.model.SessionCreateModel
import com.example.collectapp.sessioncreate.provider.model.SessionListModel
import com.google.gson.JsonObject
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface SessionApi {

    @POST(Urls.SESSION_CREATE)
    fun getSessionCreateResponse(@Header("Authorization") header : String,
                                 @Body jsonObject : JsonObject) : Single<SessionCreateModel>

    @POST(Urls.SESSION_JOIN)
    fun getSessionJoinResponse(@Header("Authorization") header : String,
                               @Body jsonObject : JsonObject) : Single<GeneralModel>

    @GET(Urls.SESSION_LIST)
    fun getSessionListResponse(@Header("Authorization") header : String) : Single<SessionListModel>

}