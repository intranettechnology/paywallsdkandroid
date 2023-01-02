package com.intranet.paywallsdk

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Request.Method.GET
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.intranet.paywallsdk.model.*
import com.intranet.paywallsdk.services.ServiceInstance
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import org.json.JSONObject


class PaywallBuilder private constructor(
    val apiClient: String?,
    val apiKey: String?,
    val baseUrl: String?,
    val payWallListener: PaywallListener?
) {

    private val disposable = CompositeDisposable()
    private val serviceInstance = ServiceInstance()

    data class Builder(
        var apiClient: String? = null,
        var apiKey: String? = null,
        var baseUrl: String? = null,
        var payWallListener: PaywallListener? = null
    ) {

        fun apiClient(apiClient: String) = apply { this.apiClient = apiClient }
        fun apiKey(apiKey: String) = apply { this.apiKey = apiKey }
        fun baseUrl(baseUrl: String) = apply {
            this.baseUrl = baseUrl
            Constants.BASE_URL = baseUrl
        }
        fun payWallListener(payWallListener: PaywallListener) =
            apply { this.payWallListener = payWallListener }

        fun build() = PaywallBuilder(apiClient, apiKey, baseUrl, payWallListener)
    }

    fun getVersion() {
        try {
            this.apiKey?.let {
                this.apiClient?.let { it1 ->
                    serviceInstance.version(it, it1)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(object :
                            DisposableSingleObserver<VersionResponse>() {
                            override fun onSuccess(response: VersionResponse) {
                                val gson = Gson()
                                val jsonString = gson.toJson(response)
                                payWallListener?.onSuccess(
                                    RequestTypes.Version.type,
                                    jsonString
                                )
                            }

                            override fun onError(e: Throwable) {
                                payWallListener?.onError(RequestTypes.Version.type,
                                    e.message.toString())
                            }
                        })
                }
            }?.let {
                disposable.add(
                    it
                )
            }
        } catch (e: Exception) {

        }
    }

    fun start3DPayment(start3DPaymentRequestModel: Start3DPaymentRequestModel) {

        try {

            this.apiKey?.let {
                this.apiClient?.let { it1 ->
                    serviceInstance.start3D(it, it1, start3DPaymentRequestModel)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(object :
                            DisposableSingleObserver<Start3DResponse>() {
                            override fun onSuccess(response: Start3DResponse) {
                                val gson = Gson()
                                val jsonString = gson.toJson(response)
                                payWallListener?.onSuccess(
                                    RequestTypes.Start3D.type,
                                    jsonString
                                )
                            }

                            override fun onError(e: Throwable) {
                                payWallListener?.onError(RequestTypes.Start3D.type,
                                    e.message.toString())
                            }
                        })
                }
            }?.let {
                disposable.add(
                    it
                )
            }
        } catch (e: Exception) {
        }
    }

    fun end3DPayment(endPaymentRequestModel: EndPaymentRequestModel) {

        try {

            this.apiKey?.let {
                this.apiClient?.let { it1 ->
                    serviceInstance.end3D(it, it1, endPaymentRequestModel)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(object :
                            DisposableSingleObserver<End3DResponse>() {
                            override fun onSuccess(response: End3DResponse) {
                                val gson = Gson()
                                val jsonString = gson.toJson(response)
                                payWallListener?.onSuccess(
                                    RequestTypes.End3D.type,
                                    jsonString
                                )
                            }

                            override fun onError(e: Throwable) {
                                payWallListener?.onError(RequestTypes.End3D.type,
                                    e.message.toString())
                            }
                        })
                }
            }?.let {
                disposable.add(
                    it
                )
            }
        } catch (e: Exception) {
        }
    }
}