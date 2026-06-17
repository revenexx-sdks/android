```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.Products

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val products = Products(client)

val result = products.productsReferenceEntityRecordsCreate(
    code = "", 
    reference_entity_id = "", 
    attribute_values = mapOf( "a" to "b" ), // (optional)
    labels = mapOf( "a" to "b" ), // (optional)
)
```
