package com.example.collectapp.helper

import android.content.Context
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    lateinit var retroClient: Retrofit
    private const val BASE_URL = Urls.BASE_URL;


    fun instantiate(context: Context): ApiClient {
        val cache = Cache(context.cacheDir, 5 * 1024 * 1024)     //5 MB
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val okHttpClient = OkHttpClient.Builder().cache(cache)
            .addInterceptor(interceptor)
            .build()
        retroClient = Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient).build()
        return this
    }

}