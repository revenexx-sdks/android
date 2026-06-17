```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.Products

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val products = Products(client)

val result = products.productsAssociationTypesUpdate(
    id = "", 
    code = "", // (optional)
    is_quantified = false, // (optional)
    is_two_way = false, // (optional)
    labels = mapOf( "a" to "b" ), // (optional)
)
```
