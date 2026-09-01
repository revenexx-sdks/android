```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.ProductsCategories
import com.revenexx.enums.Source

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val productsCategories = ProductsCategories(client)

val result = productsCategories.productsProductCategoriesList(
    limit = 1, // (optional)
    offset = 1, // (optional)
    order = "created_at.desc", // (optional)
    id = "", // (optional)
    product_id = "", // (optional)
    category_id = "", // (optional)
    position = 1, // (optional)
    source = source.MANUAL, // (optional)
    created_at = "2026-01-01T12:00:00Z", // (optional)
)
```
