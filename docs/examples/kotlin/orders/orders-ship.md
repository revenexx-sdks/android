```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.Orders

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val orders = Orders(client)

val result = orders.ordersShip(
    id = "", 
    carrier = "", // (optional)
    metadata = mapOf( "a" to "b" ), // (optional)
    number = "", // (optional)
    positions = listOf(), // (optional)
    shipped_at = "", // (optional)
    tracking_code = "", // (optional)
    tracking_url = "", // (optional)
)
```
