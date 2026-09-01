```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.Orders

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val orders = Orders(client)

val result = orders.ordersNumberRangesList(
    id = "", // (optional)
    code = "order", // (optional)
    prefix = "ORD-", // (optional)
    suffix = "", // (optional)
    padding = 6, // (optional)
    counter = 123, // (optional)
    step = 1, // (optional)
    position_step = 10, // (optional)
    channel_id = "", // (optional)
    created_at = "2026-01-01T12:00:00Z", // (optional)
    updated_at = "2026-01-01T12:00:00Z", // (optional)
    limit = 50, // (optional)
    offset = 0, // (optional)
    order = "created_at.desc", // (optional)
)
```
