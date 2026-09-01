```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.Products
import com.revenexx.enums.Kind

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val products = Products(client)

val result = products.productsGrid(
    limit = 1, // (optional)
    offset = 1, // (optional)
    order = "created_at.desc", // (optional)
    q = "cordless drill", // (optional)
    kind = kind.SIMPLE, // (optional)
    enabled = true, // (optional)
    family_id = "", // (optional)
)
```
