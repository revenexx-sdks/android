```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.Payments

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val payments = Payments(client)

val result = payments.paymentsWebhooksIngest(
    provider = "", 
    data = mapOf( "a" to "b" ), 
)
```
