```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.Markets

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val markets = Markets(client)

val result = markets.marketsLocalesCreate(
    market_id = "", 
    code = "de-DE", 
    country = "DE", 
    language = "de", 
    is_default = true, // (optional)
    position = 0, // (optional)
)
```
