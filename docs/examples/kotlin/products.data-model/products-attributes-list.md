```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.ProductsDataModel

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val productsDataModel = ProductsDataModel(client)

val result = productsDataModel.productsAttributesList(
    limit = 1, // (optional)
    offset = 1, // (optional)
    order = "created_at.desc", // (optional)
    id = "", // (optional)
    code = "net_weight", // (optional)
    entity_type = "product", // (optional)
    entity_ref = "brand", // (optional)
    type = "select", // (optional)
    group_id = "", // (optional)
    localizable = true, // (optional)
    scopable = true, // (optional)
    is_unique = true, // (optional)
    is_filterable = true, // (optional)
    usable_in_grid = true, // (optional)
    validation = "{}", // (optional)
    config = "{}", // (optional)
    labels = "{}", // (optional)
    position = 1, // (optional)
    created_at = "2026-01-01T12:00:00Z", // (optional)
    updated_at = "2026-01-01T12:00:00Z", // (optional)
)
```
