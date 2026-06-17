```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.Markets
import com.revenexx.enums.MarketStatus

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val markets = Markets(client)

val result = markets.marketsUpdate(
    id = "", 
    code = "", // (optional)
    currency = "", // (optional)
    is_default = false, // (optional)
    labels = mapOf( "a" to "b" ), // (optional)
    name = "", // (optional)
    position = 0, // (optional)
    status = MarketStatus.ACTIVE, // (optional)
)
```
