```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.Markets

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val markets = Markets(client)

val result = markets.marketsLocalesUpdate(
    market_id = "", 
    id = "", 
    code = "de-DE", // (optional)
    country = "DE", // (optional)
    is_default = true, // (optional)
    language = "de", // (optional)
    position = 0, // (optional)
)
```
