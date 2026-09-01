```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.ProductsDataModel

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val productsDataModel = ProductsDataModel(client)

val result = productsDataModel.productsAttributeOptionsUpdate(
    id = "", 
    attribute_id = "", // (optional)
    code = "stainless_steel", // (optional)
    labels = mapOf(
        "de" to "Edelstahl",
        "en" to "Stainless steel"
    ), // (optional)
    position = 1, // (optional)
    swatch = mapOf(
        "hex" to "#c0c0c0"
    ), // (optional)
)
```
