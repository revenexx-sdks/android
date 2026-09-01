```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.Prices
import com.revenexx.enums.PriceListStatus
import com.revenexx.enums.PriceListTaxBasis

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val prices = Prices(client)

val result = prices.pricesListsUpdate(
    id = "", 
    channel_id = "", // (optional)
    code = "dealer-de", // (optional)
    contact_id = "", // (optional)
    currency = "EUR", // (optional)
    description = "Contract prices for authorised dealers.", // (optional)
    is_default = true, // (optional)
    labels = mapOf(
        "de" to "Händlerpreise",
        "en" to "Dealer prices"
    ), // (optional)
    metadata = mapOf(
        "erp_price_group" to "A1",
        "source_system" to "erp"
    ), // (optional)
    name = "Dealer prices", // (optional)
    organization_id = "", // (optional)
    priority = 1, // (optional)
    requires_auth = true, // (optional)
    status = PriceListStatus.ACTIVE, // (optional)
    tax_basis = PriceListTaxBasis.NET, // (optional)
    tax_included = true, // (optional)
    valid_from = "2026-01-01T00:00:00Z", // (optional)
    valid_until = "2026-12-31T23:59:59Z", // (optional)
)
```
