```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.Markets

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val markets = Markets(client)

val result = markets.marketsTaxClassesUpdate(
    market_id = "", 
    id = "", 
    code = "standard", // (optional)
    is_default = true, // (optional)
    labels = mapOf(
        "de-DE" to "Regelsatz",
        "en-GB" to "Standard rate"
    ), // (optional)
    name = "Standard rate", // (optional)
    position = 0, // (optional)
    rate = 20, // (optional)
)
```
