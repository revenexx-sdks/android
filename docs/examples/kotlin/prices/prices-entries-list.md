```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.Prices
import com.revenexx.enums.PriceEntryType

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val prices = Prices(client)

val result = prices.pricesEntriesList(
    list_id = "", 
    id = "", // (optional)
    product_id = "", // (optional)
    sku = "BOLT-M8-30", // (optional)
    price_type = PriceEntryType.STANDARD, // (optional)
    quantity_min = 9.99, // (optional)
    unit_price = 9.99, // (optional)
    unit = "pcs", // (optional)
    valid_from = "2026-01-01T12:00:00Z", // (optional)
    valid_until = "2026-01-01T12:00:00Z", // (optional)
    created_at = "2026-01-01T12:00:00Z", // (optional)
    updated_at = "2026-01-01T12:00:00Z", // (optional)
    limit = 1, // (optional)
    offset = 1, // (optional)
    order = "created_at.desc", // (optional)
)
```
