package com.intranet.paywallsdk

import android.content.Context
import android.util.Log
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.intranet.paywallsdk.model.End3DResponse
import com.intranet.paywallsdk.model.Start3DResponse


class PaywallBuilder private constructor(
    val apiClient: String?,
    val apiKey: String?,
    val payWallListener: PaywallListener?
) {

    data class Builder(
        var apiClient: String? = null,
        var apiKey: String? = null,
        var payWallListener: PaywallListener? = null
    ) {

        fun apiClient(apiClient: String) = apply { this.apiClient = apiClient }
        fun apiKey(apiKey: String) = apply { this.apiKey = apiKey }
        fun payWallListener(payWallListener: PaywallListener) = apply { this.payWallListener = payWallListener }
        fun build() = PaywallBuilder(apiClient, apiKey, payWallListener)
    }

    fun getVersion(context: Context) {
        val queue = Volley.newRequestQueue(context)
        val url = "https://dev.itspaywall.com/api/paywall/payment/version"
        val sr: StringRequest = object : StringRequest(
            Method.GET, url,
            Response.Listener { response ->
                Log.e("HttpClient", "success! response: $response")
                              },
            Response.ErrorListener { error ->
                Log.e("HttpClient", "error: $error")
            }) {
            /*override fun getParams(): Map<String, String>? {
                val params: MutableMap<String, String> = HashMap()
                params["apiclientpublic"] = apiClient
                params["apikeypublic"] = "YOUR PASSWORD"
                return params
            }*/

            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String> {
                val params: MutableMap<String, String> = HashMap()
                //params["Content-Type"] = "application/x-www-form-urlencoded"
                params["apiclientpublic"] = apiClient.toString()
                params["apikeypublic"] = apiKey.toString()
                return params
            }
        }
        queue.add(sr)
    }

    /*fun getVersion2(context: Context) {
        val queue = Volley.newRequestQueue(context)
        val url = context.resources.getString(R.string.base_url) + "payment/version"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            object: Response.Listener<String> {
                override fun onResponse(response: String) {
                    val gson = Gson()
                    val versionResponse: VersionResponse = gson.fromJson(response, VersionResponse::class.java)
                }
            },
            object:Response.ErrorListener {
                override fun onErrorResponse(error: VolleyError?) {

                }
            }
                    override fun getHeaders(): Map<String, String> {
                // Create HashMap of your Headers as the example provided below

                val headers = HashMap<String, String>()
                headers["Content-Type"] = "application/json"
                headers["app_id"] = APP_ID
                headers["app_key"] = API_KEY

                return headers
            })

        queue!!.add(stringRequest)
    }*/

    fun start3D(context: Context) {
        val queue = Volley.newRequestQueue(context)
        val url = "https://dev.itspaywall.com/api/paywall/payment/start3d"

        val stringRequest = StringRequest(
            Request.Method.POST, url,
            object: Response.Listener<String> {
                override fun onResponse(response: String) {
                    val gson = Gson()
                    val start3DResponse: Start3DResponse = gson.fromJson(response, Start3DResponse::class.java)
                }
            },
            object:Response.ErrorListener {
                override fun onErrorResponse(error: VolleyError?) {

                }
            })
        queue!!.add(stringRequest)
    }

    fun end3D(context: Context) {
        val queue = Volley.newRequestQueue(context)
        val url = "https://dev.itspaywall.com/api/paywall/payment/end3d"

        val stringRequest = StringRequest(
            Request.Method.POST, url,
            object: Response.Listener<String> {
                override fun onResponse(response: String) {
                    val gson = Gson()
                    val end3DResponse: End3DResponse = gson.fromJson(response, End3DResponse::class.java)
                }
            },
            object:Response.ErrorListener {
                override fun onErrorResponse(error: VolleyError?) {

                }
            })
        queue!!.add(stringRequest)
    }
}