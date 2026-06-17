```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.Prices
import com.revenexx.enums.PriceListStatus

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val prices = Prices(client)

val result = prices.pricesListsCreate(
    code = "", 
    name = "", 
    channel_id = "", // (optional)
    contact_id = "", // (optional)
    currency = "", // (optional)
    description = "", // (optional)
    is_default = false, // (optional)
    labels = mapOf( "a" to "b" ), // (optional)
    market_id = "", // (optional)
    metadata = mapOf( "a" to "b" ), // (optional)
    organization_id = "", // (optional)
    priority = 0, // (optional)
    status = PriceListStatus.ACTIVE, // (optional)
    tax_included = false, // (optional)
    valid_from = "", // (optional)
    valid_until = "", // (optional)
)
```
