```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.InventoriesLocations
import com.revenexx.enums.InventoriesLocationsListType

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val inventoriesLocations = InventoriesLocations(client)

val result = inventoriesLocations.inventoriesLocationsList(
    limit = 50, // (optional)
    offset = 0, // (optional)
    order = "created_at.desc", // (optional)
    id = "", // (optional)
    code = "main", // (optional)
    name = "Main warehouse", // (optional)
    labels = "{}", // (optional)
    type = Inventories.locations.listType.WAREHOUSE, // (optional)
    priority = 0, // (optional)
    enabled = true, // (optional)
    address = "{}", // (optional)
    metadata = "{}", // (optional)
    created_at = "2026-01-01T12:00:00Z", // (optional)
    updated_at = "2026-01-01T12:00:00Z", // (optional)
)
```
