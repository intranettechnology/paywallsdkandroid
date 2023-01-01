package com.intranet.paywallsdksample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.gson.Gson
import com.intranet.paywallsdk.PaywallBuilder
import com.intranet.paywallsdk.PaywallListener
import com.intranet.paywallsdk.model.*

class MainActivity : AppCompatActivity(), PaywallListener {

    private lateinit var paywallBuilder: PaywallBuilder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        paywallBuilder = PaywallBuilder.Builder()
            .payWallListener(this)
            .apiClient("")
            .apiKey("")
            .build()

        paywallBuilder.getVersion()

        //paywallBuilder.start3DPayment(start3DPaymentRequestModel = Start3DPaymentRequestModel())
        //paywallBuilder.end3DPayment(endPaymentRequestModel = EndPaymentRequestModel())
    }

    override fun onSuccess(type: Int, response: String) {
        val gson = Gson()
        when (type) {
            RequestTypes.Version.type ->  {
                val versionResponse: VersionResponse = gson.fromJson(response, VersionResponse::class.java)
                Log.d("TAG", versionResponse.toString())
            }
            RequestTypes.Start3D.type ->  {
                val start3DResponse: Start3DResponse = gson.fromJson(response, Start3DResponse::class.java)
                start3DResponse.Body?.RedirectUrl
            }
            RequestTypes.End3D.type ->  {
                val end3DResponse: End3DResponse = gson.fromJson(response, End3DResponse::class.java)
            }
        }
    }

    override fun onError(type: Int, message: String) {
        when (type) {
            RequestTypes.Version.type ->  {
            }
            RequestTypes.Start3D.type ->  {
            }
            RequestTypes.End3D.type ->  {
            }
        }
    }
}