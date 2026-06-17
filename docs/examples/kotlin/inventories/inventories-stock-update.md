```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.Inventories

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val inventories = Inventories(client)

val result = inventories.inventoriesStockUpdate(
    id = "", 
    location_id = "", // (optional)
    metadata = mapOf( "a" to "b" ), // (optional)
    on_hand = 0, // (optional)
    product_id = "", // (optional)
    reorder_point = 0, // (optional)
    reserved = 0, // (optional)
    sku = "", // (optional)
)
```
