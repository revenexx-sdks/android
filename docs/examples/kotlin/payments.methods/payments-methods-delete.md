```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.PaymentsMethods

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val paymentsMethods = PaymentsMethods(client)

val result = paymentsMethods.paymentsMethodsDelete(
    id = "", 
)
```
