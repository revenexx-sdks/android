```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.Products

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val products = Products(client)

val result = products.productsMeasurementFamiliesUpdate(
    id = "", 
    code = "", // (optional)
    labels = mapOf( "a" to "b" ), // (optional)
    standard_unit = "", // (optional)
    units = mapOf( "a" to "b" ), // (optional)
)
```
