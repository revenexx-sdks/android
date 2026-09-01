```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.ProductsDataModel

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val productsDataModel = ProductsDataModel(client)

val result = productsDataModel.productsAssetFamiliesUpdate(
    id = "", 
    code = "packshots", // (optional)
    labels = mapOf(
        "de" to "Packshots",
        "en" to "Packshots"
    ), // (optional)
    naming_convention = mapOf(
        "allowed_extensions" to listOf("jpg", "png"),
        "pattern" to "{sku}_{index}",
        "source" to "sku"
    ), // (optional)
)
```
