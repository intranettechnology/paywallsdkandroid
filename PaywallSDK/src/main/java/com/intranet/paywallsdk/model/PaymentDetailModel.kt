package com.intranet.paywallsdk.model

import com.google.gson.annotations.SerializedName

data class PaymentDetailModel(
    @SerializedName("Amount")
    var amount: Float?,
    @SerializedName("MerchantUniqueCode")
    var merchantUniqueCode: String?,
    @SerializedName("CurrencyId")
    var currencyId: Int?,
    @SerializedName("MerchantSuccessBackUrl")
    var merchantSuccessBackUrl: String?,
    @SerializedName("MerchantFailBackUrl")
    var merchantFailBackUrl: String?,
    @SerializedName("Installement")
    var installement: Int?
)
