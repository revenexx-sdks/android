```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.Shipping

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val shipping = Shipping(client)

val result = shipping.shippingTiersUpdate(
    method_id = "", 
    id = "", 
    from_value = 0, // (optional)
    position = 0, // (optional)
    price = 0, // (optional)
)
```
