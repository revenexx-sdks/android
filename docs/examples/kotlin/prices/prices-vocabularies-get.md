```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.Prices
import com.revenexx.enums.PricesVocabulariesGetName

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val prices = Prices(client)

val result = prices.pricesVocabulariesGet(
    name = Prices.vocabularies.getName.LIST_STATUSES,
)
```
