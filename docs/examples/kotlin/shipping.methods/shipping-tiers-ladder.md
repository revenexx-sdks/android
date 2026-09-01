```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.ShippingMethods

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val shippingMethods = ShippingMethods(client)

val result = shippingMethods.shippingTiersLadder(
    method_id = "", 
    base_price = 4.9, 
    step = 5, 
    to_value = 30, 
    from_value = 0, // (optional)
    replace = true, // (optional)
    step_price = 2, // (optional)
)
```
