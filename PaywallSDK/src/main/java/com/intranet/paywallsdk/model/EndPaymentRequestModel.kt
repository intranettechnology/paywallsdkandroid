package com.intranet.paywallsdk.model

import com.google.gson.annotations.SerializedName

data class EndPaymentRequestModel(
    @SerializedName("MerchantUniqueCode")
    var merchantUniqueCode: String?
)
