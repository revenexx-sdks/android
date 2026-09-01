```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.Prices
import com.revenexx.enums.PriceEndingRule

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val prices = Prices(client)

val result = prices.pricesEntriesLadder(
    list_id = "", 
    base_price = 9.99, 
    discount_percent = 9.99, // (optional)
    product_id = "", // (optional)
    quantities = listOf(1, 10, 50), // (optional)
    replace = true, // (optional)
    rounding = PriceEndingRule.EXACT, // (optional)
    sku = "BOLT-M8-30", // (optional)
    unit = "pcs", // (optional)
)
```
