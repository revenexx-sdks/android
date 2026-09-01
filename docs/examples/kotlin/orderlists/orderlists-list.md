```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.Orderlists

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val orderlists = Orderlists(client)

val result = orderlists.orderlistsList(
    owner_id = "", // (optional)
    organization_id = "", // (optional)
    kind = "shopping", // (optional)
    limit = 50, // (optional)
    offset = 0, // (optional)
    order = "created_at.desc", // (optional)
)
```
