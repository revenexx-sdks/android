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
    carrier = "DHL", // (optional)
    metadata = mapOf(
        "warehouse" to "HAM-1"
    ), // (optional)
    number = "DEL-000123", // (optional)
    positions = listOf(), // (optional)
    shipped_at = "2026-01-01T12:00:00Z", // (optional)
    tracking_code = "00340434161234567890", // (optional)
    tracking_url = "https://example.com/track/00340434161234567890", // (optional)
)
```
