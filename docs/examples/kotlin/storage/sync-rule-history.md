```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.Storage

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val storage = Storage(client)

val result = storage.syncRuleHistory(
    rule_id = "", // (optional)
    from = "2026-01-01T12:00:00Z", // (optional)
    to = "2026-01-01T12:00:00Z", // (optional)
)
```
