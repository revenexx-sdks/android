```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.Products

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val products = Products(client)

val result = products.productsUpdate(
    id = "", 
    attribute_values = mapOf( "a" to "b" ), // (optional)
    completeness = mapOf( "a" to "b" ), // (optional)
    deleted_at = "", // (optional)
    enabled = false, // (optional)
    family_id = "", // (optional)
    family_variant_id = "", // (optional)
    kind = "", // (optional)
    parent_id = "", // (optional)
    quantified_associations = mapOf( "a" to "b" ), // (optional)
    sku = "", // (optional)
    tax_class = "", // (optional)
)
```
