```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.Products

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val products = Products(client)

val result = products.productsAttributeOptionsUpdate(
    id = "", 
    attribute_id = "", // (optional)
    code = "", // (optional)
    labels = mapOf( "a" to "b" ), // (optional)
    position = 0, // (optional)
    swatch = mapOf( "a" to "b" ), // (optional)
)
```
