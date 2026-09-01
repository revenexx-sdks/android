```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.Products

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val products = Products(client)

val result = products.productsProductAssociationsList(
    limit = 1, // (optional)
    offset = 1, // (optional)
    order = "created_at.desc", // (optional)
    id = "", // (optional)
    product_id = "", // (optional)
    association_type_id = "", // (optional)
    target_product_id = "", // (optional)
    quantity = 9.99, // (optional)
    position = 1, // (optional)
    created_at = "2026-01-01T12:00:00Z", // (optional)
)
```
