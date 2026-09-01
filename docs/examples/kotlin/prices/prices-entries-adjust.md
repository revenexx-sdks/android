```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.Prices
import com.revenexx.enums.PriceEndingRule

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val prices = Prices(client)

val result = prices.pricesEntriesAdjust(
    list_id = "", 
    amount = 9.99, // (optional)
    dry_run = true, // (optional)
    percent = 9.99, // (optional)
    rounding = PriceEndingRule.EXACT, // (optional)
    sku_prefix = "BOLT-", // (optional)
)
```
