```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.Orderlists

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val orderlists = Orderlists(client)

val result = orderlists.orderlistsCreate(
    name = "Weekly office supplies", 
    owner_id = "", 
    owner_name = "Jamie Rivera", 
    items = listOf(), // (optional)
    kind = "shopping", // (optional)
    metadata = mapOf(
        "department" to "facility",
        "erp_reference" to "REQ-2026-0042"
    ), // (optional)
    organization_id = "", // (optional)
    shared = true, // (optional)
)
```
