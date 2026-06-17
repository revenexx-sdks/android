```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.Products

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val products = Products(client)

val result = products.productsFamilyAttributesCreate(
    attribute_id = "", 
    family_id = "", 
    is_required = false, // (optional)
    position = 0, // (optional)
    required_channels = mapOf( "a" to "b" ), // (optional)
)
```
