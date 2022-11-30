package com.intranet.paywallsdk.services

import com.intranet.paywallsdk.Constants
import com.intranet.paywallsdk.model.*
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.POST

interface Services {
    @GET(Constants.VERSION)
    fun version(@HeaderMap apiKey: Map<String, String>, @HeaderMap apiClient: Map<String, String>): Single<VersionResponse>

    @POST(Constants.START3D)
    fun start3DPayment(@HeaderMap apiKey: Map<String, String>, @HeaderMap apiClient: Map<String, String>, @Body start3DPaymentRequestModel: Start3DPaymentRequestModel): Single<Start3DResponse>

    @POST(Constants.END3D)
    fun end3DPayment(@HeaderMap apiKey: Map<String, String>, @HeaderMap apiClient: Map<String, String>, @Body endPaymentRequestModel: EndPaymentRequestModel): Single<End3DResponse>
}