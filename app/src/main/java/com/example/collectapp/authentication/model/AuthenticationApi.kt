package com.example.collectapp.authentication.model

import com.example.collectapp.helper.Urls
import com.google.gson.JsonObject
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthenticationApi {

    @POST(Urls.SIGN_IN)
    fun getSignInResponse(@Body jsonObject: JsonObject) : Single<AuthenticationModel>

    @POST(Urls.SIGN_UP)
    fun getSignUpResponse(@Body jsonObject: JsonObject) : Single<AuthenticationModel>

    @POST(Urls.SIGN_UP_OTP)
    fun getSignUpOtpResponse(@Body jsonObject: JsonObject) : Single<AuthenticationModel>

    @POST(Urls.RESET_PASSWORD)
    fun getForgotPasswordResponse(@Body jsonObject: JsonObject) : Single<AuthenticationModel>

}