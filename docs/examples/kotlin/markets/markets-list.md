```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.Markets
import com.revenexx.enums.MarketsListStatus

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val markets = Markets(client)

val result = markets.marketsList(
    id = "", // (optional)
    code = "northwind", // (optional)
    name = "Northwind", // (optional)
    labels = "{"de-DE":"Nordwind","en-GB":"Northwind"}", // (optional)
    currency = "EUR", // (optional)
    status = Markets.listStatus.ACTIVE, // (optional)
    is_default = false, // (optional)
    position = 0, // (optional)
    created_at = "2026-01-01T12:00:00Z", // (optional)
    updated_at = "2026-01-01T12:00:00Z", // (optional)
    limit = 50, // (optional)
    offset = 0, // (optional)
    order = "position.asc", // (optional)
)
```
