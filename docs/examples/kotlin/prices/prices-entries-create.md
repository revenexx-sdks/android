```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.Prices
import com.revenexx.enums.PriceEntryType

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val prices = Prices(client)

val result = prices.pricesEntriesCreate(
    list_id = "", 
    metadata = mapOf( "a" to "b" ), // (optional)
    price_type = PriceEntryType.STANDARD, // (optional)
    product_id = "", // (optional)
    quantity_min = 0, // (optional)
    sku = "", // (optional)
    unit = "", // (optional)
    unit_price = 0, // (optional)
    valid_from = "", // (optional)
    valid_until = "", // (optional)
)
```
