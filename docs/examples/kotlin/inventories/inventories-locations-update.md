```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.Inventories
import com.revenexx.enums.LocationType

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val inventories = Inventories(client)

val result = inventories.inventoriesLocationsUpdate(
    id = "", 
    address = mapOf( "a" to "b" ), // (optional)
    code = "", // (optional)
    enabled = false, // (optional)
    labels = mapOf( "a" to "b" ), // (optional)
    metadata = mapOf( "a" to "b" ), // (optional)
    name = "", // (optional)
    priority = 0, // (optional)
    type = LocationType.WAREHOUSE, // (optional)
)
```
