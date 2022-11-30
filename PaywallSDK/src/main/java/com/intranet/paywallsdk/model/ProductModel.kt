package com.intranet.paywallsdk.model

import com.google.gson.annotations.SerializedName

data class ProductModel(
    @SerializedName("ProductId")
    var productId: String?,
    @SerializedName("ProductName")
    var productName: String?,
    @SerializedName("ProductCategory")
    var productCategory: String?,
    @SerializedName("ProductDescription")
    var productDescription: String?,
    @SerializedName("ProductAmount")
    var productAmount: Float?
)
