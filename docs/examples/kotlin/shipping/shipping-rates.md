```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.Shipping

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val shipping = Shipping(client)

val result = shipping.shippingRates(
    attributes = mapOf( "a" to "b" ), // (optional)
    country = "", // (optional)
    currency = "", // (optional)
    market_id = "", // (optional)
    order_value = 0, // (optional)
    quantity = 0, // (optional)
    weight = 0, // (optional)
)
```
