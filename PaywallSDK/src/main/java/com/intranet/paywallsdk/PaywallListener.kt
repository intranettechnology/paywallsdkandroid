package com.intranet.paywallsdk

import android.text.BoringLayout

interface PaywallListener {
    fun onSuccess(type: Int, response: String)
    fun onError(type: Int, message: String)
}