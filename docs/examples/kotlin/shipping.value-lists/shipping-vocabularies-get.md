```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.ShippingValueLists
import com.revenexx.enums.ShippingVocabulariesGetName

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val shippingValueLists = ShippingValueLists(client)

val result = shippingValueLists.shippingVocabulariesGet(
    name = Shipping.vocabularies.getName.CARRIER_STATUSES,
)
```
