# paywallsdkandroid


## Installation

```kotlin
implementation 'com.github.intranettechnology:paywallsdkandroid:Beta-1.0.2'

            .
            .
    repositories {
        maven { url 'https://jitpack.io' } //add jitapck repository
    }
            .
            .
```

## Kotlin Usage

Inherit 'Paywall Listener' to activity or fragment for detect your functions result. It will override 2 function which are called 'OnSuccess' and 'OnError'.

```kotlin
MainActivity : AppCompatActivity(), PaywallListener {
              .
              .
    override fun onSuccess(type: Int, response: String) {

    }

    override fun onError(type: Int, message: String) {

    }
              .
              .
}
```

To Initialize Paywall builder need to have apiclient and apikey. Than you can use all functions which PaywallSDK has.

```kotlin
                .
                .
private lateinit var paywallBuilder: PaywallBuilder
                .
                .
                
paywallBuilder = PaywallBuilder.Builder()
            .payWallListener(this)
            .apiClient("YOUR_API_CLIENT")
            .apiKey("YOUR_API_KEY")
            .build()
```
## Functions

Version:

```kotlin
paywallBuilder.getVersion() // get version information
        .
        .
override fun onSuccess(type: Int, response: String) {
        val gson = Gson()
        when (type) {
                        .
                        .
            RequestTypes.Version.type ->  { // get version response
                val versionResponse: VersionResponse = gson.fromJson(response, VersionResponse::class.java) //Convert json to Version Response Model
                Log.d(TAG, versionResponse.toString())
                        .
                        .
            }
        }
    }
        .
        .

```

Start3d Payment:

First of all, you have to fill the "Start3DPaymentRequestModel" to start 3D Payment.
```kotlin

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
```

```kotlin
paywallBuilder.start3DPayment(start3DPaymentRequestModel = Start3DPaymentRequestModel()) // start 3D payment
            .
            .
override fun onSuccess(type: Int, response: String) {
        val gson = Gson()
        when (type) {
                        .
                        .
            RequestTypes.Start3D.type ->  { // get start 3D payment response
                val start3DResponse: Start3DResponse = gson.fromJson(response, Start3DResponse::class.java) //Convert json to Start3DResponse
                Log.d(TAG, start3DResponse.toString())
                
                start3DResponse.Body?.RedirectUrl // You can open web page with 'RedirectUrl' to show 3D screen.
                        .
                        .
            }
        }
    }
          .
          .
```

End3d Payment:

When you take response from 3D screen you have to call this request.

Fill the "EndPaymentRequestModel" to End 3D Payment.

```kotlin
data class EndPaymentRequestModel(
    @SerializedName("MerchantUniqueCode")
    var merchantUniqueCode: String?
)
```
```kotlin
paywallBuilder.end3DPayment(endPaymentRequestModel = EndPaymentRequestModel()) // end 3D payment
            .
            .
override fun onSuccess(type: Int, response: String) {
        val gson = Gson()
        when (type) {
                        .
                        .
            RequestTypes.End3D.type ->  { // get end 3D payment response
                val end3DResponse: End3DResponse = gson.fromJson(response, End3DResponse::class.java) //Convert json to End3DResponse
                Log.d(TAG, end3DResponse.toString())
                        .
                        .
            }
        }
    }
          .
          .
```

Catch the error message

```kotlin
 override fun onError(type: Int, message: String) {
        when (type) {
            RequestTypes.Version.type ->  {
            }
            RequestTypes.Start3D.type ->  {
            }
            RequestTypes.End3D.type ->  {
            }
        }
    }
```
Request Types:
```kotlin
enum class RequestTypes(val type: Int) {
    Version(1),
    Start3D(2),
    End3D(3)
}
```
