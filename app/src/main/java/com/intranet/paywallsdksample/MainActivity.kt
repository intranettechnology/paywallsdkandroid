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
            .apiClient("bd5a1d8b-50f9-4c17-8a8c-93260af5f0ac")
            .apiKey("xU2wYylAsO8xa5zL0oO7moLXWchYVYjiw0xYq6qivKUDEPraTiXr2ATWL+V/QkVJWieXKhPb93OPJPKHja9VIMg8N7CCo2PxiIyTFA8fHqla0XgHXn+5EReThF39QFAJlZUtnGAExzM27ITtoXG8dsFo4d6aJjI5YWrcbWtA4aEoi8viucQek/J2oA5ClwNFIjgyY9goJn0zxWyv+xxjgJWCfh0vBUtxt826hD386X5EVqrclip7DEPTaq6um47E9vb/3XGrBTWQcfO3RH9600Nn2AhvJZ7K7y9qpFCBrYkBub2adRAyGqdViGoQpuFzqQTiq3u+E9ydBi/b50oGfQ==")
            .baseUrl("https://dev-payment-api.itspaywall.com/api/paywall/")
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
                Log.d("TAG", message)
            }
            RequestTypes.Start3D.type ->  {
            }
            RequestTypes.End3D.type ->  {
            }
        }
    }
}