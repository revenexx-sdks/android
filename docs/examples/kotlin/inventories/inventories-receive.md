```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.Inventories

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val inventories = Inventories(client)

val result = inventories.inventoriesReceive(
    items = listOf(), 
    location_code = "", // (optional)
    reason = "", // (optional)
)
```
