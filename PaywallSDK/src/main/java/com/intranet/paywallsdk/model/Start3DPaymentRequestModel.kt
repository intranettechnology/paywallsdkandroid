package com.intranet.paywallsdk.model

import com.google.gson.annotations.SerializedName

data class Start3DPaymentRequestModel(
    @SerializedName("PaymentDetail")
    var paymentDetail: PaymentDetailModel?,
    @SerializedName("Card")
    var card: CardModel?,
    @SerializedName("Customer")
    var customer: CustomerModel?,
    @SerializedName("Products")
    var products: ArrayList<ProductModel>?
)
