```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.InventoriesStock
import com.revenexx.enums.InventoriesMovementsListType

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val inventoriesStock = InventoriesStock(client)

val result = inventoriesStock.inventoriesMovementsList(
    limit = 50, // (optional)
    offset = 0, // (optional)
    order = "created_at.desc", // (optional)
    id = "", // (optional)
    location_id = "", // (optional)
    product_id = "", // (optional)
    sku = "ACME-4711-BLK", // (optional)
    type = Inventories.movements.listType.INBOUND, // (optional)
    quantity = 5, // (optional)
    order_ref = "SO-2026-000123", // (optional)
    reason = "Delivery note 4711", // (optional)
    metadata = "{}", // (optional)
    created_at = "2026-01-01T12:00:00Z", // (optional)
)
```
