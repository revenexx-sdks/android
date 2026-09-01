```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.Orders

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val orders = Orders(client)

val result = orders.ordersNumberRangesCreate(
    code = "order", 
    channel_id = "", // (optional)
    counter = 123, // (optional)
    metadata = mapOf(
        "owner" to "erp-sync"
    ), // (optional)
    padding = 6, // (optional)
    position_step = 10, // (optional)
    prefix = "ORD-", // (optional)
    step = 1, // (optional)
    suffix = "", // (optional)
)
```
