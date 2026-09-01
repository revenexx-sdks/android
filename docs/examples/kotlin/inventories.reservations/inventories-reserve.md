```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.InventoriesReservations

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val inventoriesReservations = InventoriesReservations(client)

val result = inventoriesReservations.inventoriesReserve(
    order_ref = "SO-2026-000123", 
    expires_at = "2026-01-01T12:00:00Z", // (optional)
    items = listOf(), // (optional)
    location_code = "main", // (optional)
    product_id = "", // (optional)
    quantity = 2, // (optional)
    ship_to = mapOf( "a" to "b" ), // (optional)
    sku = "ACME-4711-BLK", // (optional)
)
```
