```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.InventoriesLocations
import com.revenexx.enums.LocationType

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val inventoriesLocations = InventoriesLocations(client)

val result = inventoriesLocations.inventoriesLocationsUpdate(
    id = "", 
    address = mapOf(
        "city" to "Nuremberg",
        "country" to "DE",
        "postal_code" to "90402",
        "street" to "Industriering 4"
    ), // (optional)
    code = "main", // (optional)
    enabled = true, // (optional)
    labels = mapOf(
        "de" to "Hauptlager",
        "en" to "Main warehouse"
    ), // (optional)
    metadata = mapOf(
        "erp_site" to "1000"
    ), // (optional)
    name = "Main warehouse", // (optional)
    priority = 0, // (optional)
    type = LocationType.WAREHOUSE, // (optional)
)
```
