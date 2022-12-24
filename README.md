# paywallsdkandroid


## Projeye Ekleme

```kotlin
implementation 'com.github.intranettechnology:paywallsdkandroid:Beta-1.0.2'

            .
            .
    repositories {
        maven { url 'https://jitpack.io' } // jitapck repository ekleyin
    }
            .
            .
```

## Kotlin Usage

'Paywall Listener' interface'ini fragment veya aktiviteye ekleyin. Başarılı veya başarısız sonuçları takip edebileceğiniz 2 fonksiyon ekleyecektir.

```kotlin
MainActivity : AppCompatActivity(), PaywallListener {
             
    override fun onSuccess(type: Int, response: String) {

    }

    override fun onError(type: Int, message: String) {

    }
}
```

Paywall SDK'i kullanabilmek için Paywall ekibinden almış olduğunuz "publicapikey" ve "publicapiclient" parametrelerine ihtiyacınız var. Bunlarla birlikte aşağıdaki şekilde PaywallBuilder' ı ilklendirebilirsiniz.

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
paywallBuilder.getVersion() // versiyon bilgisini alın
        
        
override fun onSuccess(type: Int, response: String) {
        val gson = Gson()
        when (type) {
            RequestTypes.Version.type ->  { // versiyon yanıtı
                val versionResponse: VersionResponse = gson.fromJson(response, VersionResponse::class.java)
                Log.d(TAG, versionResponse.toString())
            }
        }
    }

```

3D Ödeme başlat:

3D ödeme başlatmak için "Start3DPaymentRequestModel" modelini doldurmanız gerekmektedir. Bu model içerisinde "PaymentDetail" objesindeki "MerchantUniqueCode" parametresine her defasında unique bir kod vermelisiniz. Bu kod daha sonra ödeme bitirme işleminde kullanılacaktır. "MerchantSuccessBackUrl" parametresinde, 3D ödeme ekranında başarılı sonuç alındığı takdirde kullanıcının ilerlemesini istediğiniz sayfa ve aynı zamanda sizin takip edeceğiniz bir url eklemeniz gerekmektedir. "MerchantFailBackUrl" parametresinde, 3D ödeme ekranında başarısız sonuç alındığı takdirde kullanıcının ilerlemesini istediğiniz sayfa ve aynı zamanda sizin takip edeceğiniz bir url eklemeniz gerekmektedir.
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
paywallBuilder.start3DPayment(start3DPaymentRequestModel = Start3DPaymentRequestModel()) // 3D ödeme başlat
            .
            .
override fun onSuccess(type: Int, response: String) {
        val gson = Gson()
        when (type) {
            RequestTypes.Start3D.type ->  { // 3D ödeme yanıtı
                val start3DResponse: Start3DResponse = gson.fromJson(response, Start3DResponse::class.java)
                Log.d(TAG, start3DResponse.toString())
                
                start3DResponse.Body?.RedirectUrl // 'RedirectUrl' parametresiyle 3D ödeme ekranına ilerleyebilirsiniz.                      
            }
        }
    }
          .
          .
```

3D Ödeme sonlandırma:

3D Ödeme ekranından aldığınız yanıtn ardından 3D Ödeme metodunu çağırmalısınız. Bu metodunun yanıtında alacağınız başarısız veya başarılı bilgisi ödeme işleminin aynı zamanda sonucu olacaktır.

3D Ödeme sonlandırmak için "EndPaymentRequestModel" modelini doldurmanız gerekmektedir. Bu modeldeki "MerchantUniqueCode", ödeme başlatırken kullandığınız kullandığınız "MerchantUniqueCode" parametresiyle aynı olmalıdır.

```kotlin
data class EndPaymentRequestModel(
    @SerializedName("MerchantUniqueCode")
    var merchantUniqueCode: String?
)
```
```kotlin
paywallBuilder.end3DPayment(endPaymentRequestModel = EndPaymentRequestModel()) // Ödeme sonlandırma
            .
            .
override fun onSuccess(type: Int, response: String) {
        val gson = Gson()
        when (type) {
                        .
                        .
            RequestTypes.End3D.type ->  { // Ödeme sonlandırma yanıtı
                val end3DResponse: End3DResponse = gson.fromJson(response, End3DResponse::class.java)
                Log.d(TAG, end3DResponse.toString())
                        .
                        .
            }
        }
    }
          .
          .
```

Hata mesajlarını yakalayın

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
İstek Çeşitleri:
```kotlin
enum class RequestTypes(val type: Int) {
    Version(1),
    Start3D(2),
    End3D(3)
}
```
