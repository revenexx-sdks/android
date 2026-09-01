```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.InventoriesStock

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val inventoriesStock = InventoriesStock(client)

val result = inventoriesStock.inventoriesReceive(
    items = listOf(), // (optional)
    location_code = "main", // (optional)
    product_id = "", // (optional)
    quantity = 12, // (optional)
    reason = "Delivery note 4711", // (optional)
    sku = "ACME-4711-BLK", // (optional)
)
```
