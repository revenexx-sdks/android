```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.ProductsDataModel

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val productsDataModel = ProductsDataModel(client)

val result = productsDataModel.productsFamilyVariantsUpdate(
    id = "", 
    axes = mapOf(
        "0" to "colour",
        "1" to "size"
    ), // (optional)
    code = "clothing_by_colour_size", // (optional)
    family_id = "", // (optional)
    labels = mapOf(
        "de" to "Nach Farbe und Größe",
        "en" to "By colour and size"
    ), // (optional)
)
```
