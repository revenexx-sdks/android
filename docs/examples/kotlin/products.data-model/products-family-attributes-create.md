```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.ProductsDataModel

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val productsDataModel = ProductsDataModel(client)

val result = productsDataModel.productsFamilyAttributesCreate(
    attribute_id = "", 
    family_id = "", 
    is_required = true, // (optional)
    position = 1, // (optional)
    required_channels = mapOf(
        "0" to "shop",
        "1" to "b2b"
    ), // (optional)
)
```
