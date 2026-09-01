```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.PaymentsLedger

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val paymentsLedger = PaymentsLedger(client)

val result = paymentsLedger.paymentsOrdersCapture(
    order_ref = "ORD-10042", 
)
```
