```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.Products

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val products = Products(client)

val result = products.productsAttributesUpdate(
    id = "", 
    code = "", // (optional)
    config = mapOf( "a" to "b" ), // (optional)
    entity_ref = "", // (optional)
    entity_type = "", // (optional)
    group_id = "", // (optional)
    is_filterable = false, // (optional)
    is_unique = false, // (optional)
    labels = mapOf( "a" to "b" ), // (optional)
    localizable = false, // (optional)
    position = 0, // (optional)
    scopable = false, // (optional)
    type = "", // (optional)
    usable_in_grid = false, // (optional)
    validation = mapOf( "a" to "b" ), // (optional)
)
```
