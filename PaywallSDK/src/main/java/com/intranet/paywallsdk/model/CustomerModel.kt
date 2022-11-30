package com.intranet.paywallsdk.model

import com.google.gson.annotations.SerializedName

data class CustomerModel(
    @SerializedName("FullName")
    var fullName: String?,
    @SerializedName("Phone")
    var phone: String?,
    @SerializedName("Email")
    var email: String?,
    @SerializedName("Country")
    var country: String?,
    @SerializedName("City")
    var city: String?,
    @SerializedName("Address")
    var address: String?,
    @SerializedName("IdentityNumber")
    var identityNumber: String?,
    @SerializedName("TaxNumber")
    var taxNumber: String?
)
