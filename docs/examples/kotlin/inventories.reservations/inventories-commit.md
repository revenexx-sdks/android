```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.InventoriesReservations

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val inventoriesReservations = InventoriesReservations(client)

val result = inventoriesReservations.inventoriesCommit(
    order_ref = "SO-2026-000123", 
)
```
