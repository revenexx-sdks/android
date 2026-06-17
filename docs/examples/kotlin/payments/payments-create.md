```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.Payments

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val payments = Payments(client)

val result = payments.paymentsCreate(
    amount = 0, 
    method_code = "", 
    cart_id = "", // (optional)
    contact_id = "", // (optional)
    country = "", // (optional)
    currency = "", // (optional)
    idempotency_key = "", // (optional)
    metadata = mapOf( "a" to "b" ), // (optional)
    order_ref = "", // (optional)
    return_url = "", // (optional)
)
```
