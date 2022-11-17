package com.intranet.paywallsdk.model

data class Start3DResponse(
    var ErrorCode: Int?,
    var Result: Boolean?,
    var Message: String?,
    var Body: Start3DResponseBody?
)

data class Start3DResponseBody(
    var Message: String?,
    var RedirectUrl: String?
)