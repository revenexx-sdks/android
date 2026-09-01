```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.PaymentsLedger

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val paymentsLedger = PaymentsLedger(client)

val result = paymentsLedger.paymentsCreate(
    amount = 49.9, 
    method_code = "invoice", 
    cart_id = "", // (optional)
    contact_id = "", // (optional)
    country = "DE", // (optional)
    currency = "EUR", // (optional)
    idempotency_key = "checkout-2f9c41", // (optional)
    metadata = mapOf(
        "order_source" to "web"
    ), // (optional)
    order_ref = "ORD-10042", // (optional)
    return_url = "https://shop.example.com/checkout/return", // (optional)
)
```
