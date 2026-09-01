```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.Prices
import com.revenexx.enums.PriceEntryType

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val prices = Prices(client)

val result = prices.pricesEntriesUpdate(
    list_id = "", 
    id = "", 
    metadata = mapOf(
        "imported_batch" to "2026-02-14",
        "source_system" to "erp"
    ), // (optional)
    price_type = PriceEntryType.STANDARD, // (optional)
    product_id = "", // (optional)
    quantity_min = 9.99, // (optional)
    sku = "BOLT-M8-30", // (optional)
    unit = "pcs", // (optional)
    unit_price = 9.99, // (optional)
    valid_from = "2026-03-01T00:00:00Z", // (optional)
    valid_until = "2026-03-31T23:59:59Z", // (optional)
)
```
