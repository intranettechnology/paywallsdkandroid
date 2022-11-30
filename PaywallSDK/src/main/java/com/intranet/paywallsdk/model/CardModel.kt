package com.intranet.paywallsdk.model

import com.google.gson.annotations.SerializedName

data class CardModel(
    @SerializedName("OwnerName")
    var ownerName: String?,
    @SerializedName("Number")
    var number: String?,
    @SerializedName("ExpireMonth")
    var expireMonth: String?,
    @SerializedName("ExpireYear")
    var expireYear: String?,
    @SerializedName("Cvv")
    var cvv: String?,
    @SerializedName("UniqueCode")
    var uniqueCode: String?
)
