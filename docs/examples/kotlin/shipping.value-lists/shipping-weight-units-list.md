```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.ShippingValueLists

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val shippingValueLists = ShippingValueLists(client)

val result = shippingValueLists.shippingWeightUnitsList(
    limit = 1, // (optional)
    offset = 1, // (optional)
)
```
