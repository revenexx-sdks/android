```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.Markets
import com.revenexx.enums.MarketStatus

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val markets = Markets(client)

val result = markets.marketsClone(
    id = "northwind", 
    code = "northwind-b2b", 
    copy_currencies = true, // (optional)
    copy_locales = true, // (optional)
    copy_tax_classes = true, // (optional)
    currency = "EUR", // (optional)
    name = "Northwind B2B", // (optional)
    status = MarketStatus.ACTIVE, // (optional)
)
```
