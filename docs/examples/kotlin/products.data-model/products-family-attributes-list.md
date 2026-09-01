```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.ProductsDataModel

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val productsDataModel = ProductsDataModel(client)

val result = productsDataModel.productsFamilyAttributesList(
    limit = 1, // (optional)
    offset = 1, // (optional)
    order = "created_at.desc", // (optional)
    id = "", // (optional)
    family_id = "", // (optional)
    attribute_id = "", // (optional)
    position = 1, // (optional)
    is_required = true, // (optional)
    required_channels = "[]", // (optional)
    created_at = "2026-01-01T12:00:00Z", // (optional)
)
```
