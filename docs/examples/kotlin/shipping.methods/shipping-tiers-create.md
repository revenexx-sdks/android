```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.ShippingMethods

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val shippingMethods = ShippingMethods(client)

val result = shippingMethods.shippingTiersCreate(
    method_id = "", 
    from_value = 10, // (optional)
    position = 1, // (optional)
    price = 6.9, // (optional)
)
```
