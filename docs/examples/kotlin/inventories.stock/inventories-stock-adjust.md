```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.InventoriesStock

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val inventoriesStock = InventoriesStock(client)

val result = inventoriesStock.inventoriesStockAdjust(
    id = "", 
    quantity = -3, 
    reason = "Stocktake 2026-03, two units damaged", // (optional)
)
```
