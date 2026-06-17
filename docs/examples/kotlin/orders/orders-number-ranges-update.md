```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.Orders

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val orders = Orders(client)

val result = orders.ordersNumberRangesUpdate(
    id = "", 
    channel_id = "", // (optional)
    code = "", // (optional)
    counter = 0, // (optional)
    metadata = mapOf( "a" to "b" ), // (optional)
    padding = 0, // (optional)
    position_step = 0, // (optional)
    prefix = "", // (optional)
    step = 0, // (optional)
    suffix = "", // (optional)
)
```
