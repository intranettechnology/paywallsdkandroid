package com.intranet.paywallsdk.services

import com.intranet.paywallsdk.Constants
import com.intranet.paywallsdk.model.*
import io.reactivex.Single
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ServiceInstance {

    private val okHttpClient: OkHttpClient = OkHttpClient.Builder()
        .readTimeout(60, TimeUnit.SECONDS)
        .connectTimeout(60, TimeUnit.SECONDS)
        .build()

    private val serviceBuild =
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(Services::class.java)

    fun version(apiKey: String, apiClient: String): Single<VersionResponse> {

        val apiKeyMap: Map<String, String> = mapOf(
            Constants.apiKey to apiKey
        )

        val apiClientMap: Map<String, String> = mapOf(
            Constants.apiClient to apiClient
        )

        return serviceBuild.version(
            apiKeyMap, apiClientMap
        )
    }

    fun start3D(apiKey: String, apiClient: String, start3DPaymentRequestModel: Start3DPaymentRequestModel): Single<Start3DResponse> {

        val apiKeyMap: Map<String, String> = mapOf(
            Constants.apiKey to apiKey
        )

        val apiClientMap: Map<String, String> = mapOf(
            Constants.apiClient to apiClient
        )

        return serviceBuild.start3DPayment(
            apiKeyMap, apiClientMap, start3DPaymentRequestModel
        )
    }

    fun end3D(apiKey: String, apiClient: String, endPaymentRequestModel: EndPaymentRequestModel): Single<End3DResponse> {

        val apiKeyMap: Map<String, String> = mapOf(
            Constants.apiKey to apiKey
        )

        val apiClientMap: Map<String, String> = mapOf(
            Constants.apiClient to apiClient
        )

        return serviceBuild.end3DPayment(
            apiKeyMap, apiClientMap, endPaymentRequestModel
        )
    }
}