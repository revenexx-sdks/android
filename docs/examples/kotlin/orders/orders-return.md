```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.Orders

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val orders = Orders(client)

val result = orders.ordersReturn(
    id = "", 
    metadata = mapOf(
        "rma_portal_case" to "C-2026-0917"
    ), // (optional)
    positions = listOf(), // (optional)
    reason = "Damaged on arrival", // (optional)
    restock = true, // (optional)
)
```
