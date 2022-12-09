# paywallsdkandroid


## Installation

```kotlin
implementation 'com.github.intranettechnology:paywallsdkandroid:Beta-1.0.2'
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
paywallBuilder.getVersion()

```

Start3d Payment:

```kotlin
paywallBuilder.start3DPayment(start3DPaymentRequestModel = Start3DPaymentRequestModel())

```

End3d Payment:

```kotlin
paywallBuilder.end3DPayment(endPaymentRequestModel = EndPaymentRequestModel())

```
