```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.InventoriesStock

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val inventoriesStock = InventoriesStock(client)

val result = inventoriesStock.inventoriesStockUpdate(
    id = "", 
    location_id = "", // (optional)
    metadata = mapOf(
        "backorder" to true
    ), // (optional)
    product_id = "", // (optional)
    reorder_point = 10, // (optional)
    sku = "ACME-4711-BLK", // (optional)
)
```
