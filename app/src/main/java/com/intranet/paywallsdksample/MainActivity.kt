package com.intranet.paywallsdksample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.gson.Gson
import com.intranet.paywallsdk.PaywallBuilder
import com.intranet.paywallsdk.PaywallListener
import com.intranet.paywallsdk.model.*

class MainActivity : AppCompatActivity(), PaywallListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val paywallBuilder = PaywallBuilder.Builder()
            .payWallListener(this)
            .apiClient("28131983-6a16-47f0-a6eb-9ca10194d627")
            .apiKey("CBgUeMc64GLGZmYSQCQw0rsMUemTXnboa51YKsdDRLXPfPuLtu4zHyoJenmpX6PORCO/oiGqtQUpNaDuKXypMSdtm486ubGTFceeSaxq/k+rEylrsQSyjFhokyNFFotg3rWWwz/LD1Vfh4vhhpyzndbSEZNRKnE4crxPdGpLzpT4TZHwHVFiV72x/Fr0Ed12F1FWvoolL33RohvM614NiQJsePHhJGE+y3PhYf7RxQifjwdTVHIJ1rfj127PMt3o+mKiGwhlmgCW4hsN9Ti6MU2NBEPVCjcmtv1UnKv3iRa33KO4XaYF+Gmp6xuLolqi4XAyP2up4ccmjNu+8swnkg==")
            .build()

        paywallBuilder.getVersion()
        //paywallBuilder.start3DPayment(this)
        //paywallBuilder.end3D(this)
    }

    override fun onSuccess(type: Int, response: String) {
        val gson = Gson()
        when (type) {
            RequestTypes.Version.type ->  {
                val versionResponse: VersionResponse = gson.fromJson(response, VersionResponse::class.java)
            }
            RequestTypes.Start3D.type ->  {
                val start3DResponse: Start3DResponse = gson.fromJson(response, Start3DResponse::class.java)
            }
            RequestTypes.End3D.type ->  {
                val end3DResponse: End3DResponse = gson.fromJson(response, End3DResponse::class.java)
            }
        }

    }

    override fun onError(type: Int, message: String) {

    }
}