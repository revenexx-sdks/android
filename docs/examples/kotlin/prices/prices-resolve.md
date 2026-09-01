```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.Prices

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val prices = Prices(client)

val result = prices.pricesResolve(
    items = listOf(), 
    at = "2026-03-15T09:00:00Z", // (optional)
    channel_id = "", // (optional)
    contact_id = "", // (optional)
    currency = "EUR", // (optional)
    market_id = "", // (optional)
    organization_id = "", // (optional)
)
```
