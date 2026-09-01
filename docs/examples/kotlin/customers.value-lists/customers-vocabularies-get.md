```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.CustomersValueLists
import com.revenexx.enums.CustomersVocabulariesGetName

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val customersValueLists = CustomersValueLists(client)

val result = customersValueLists.customersVocabulariesGet(
    name = Customers.vocabularies.getName.ADDRESS_TYPES,
)
```
