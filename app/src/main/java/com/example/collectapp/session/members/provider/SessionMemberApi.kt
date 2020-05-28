package com.example.collectapp.session.members.provider

import com.example.collectapp.helper.Urls
import com.example.collectapp.session.members.provider.model.MembersListModel
import com.google.gson.JsonObject
import io.reactivex.Single
import org.json.JSONObject
import retrofit2.http.*

interface SessionMemberApi {

    @GET(Urls.SESSION_MEMBERS)
    fun getMemberList(@Query("sessionId") sessionId: Long) : Single<MembersListModel>

}