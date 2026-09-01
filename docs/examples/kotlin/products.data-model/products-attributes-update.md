```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.ProductsDataModel

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val productsDataModel = ProductsDataModel(client)

val result = productsDataModel.productsAttributesUpdate(
    id = "", 
    code = "net_weight", // (optional)
    config = mapOf(
        "reference_entity" to "brand"
    ), // (optional)
    entity_ref = "brand", // (optional)
    entity_type = "product", // (optional)
    group_id = "", // (optional)
    is_filterable = true, // (optional)
    is_unique = true, // (optional)
    labels = mapOf(
        "de" to "Nettogewicht",
        "en" to "Net weight"
    ), // (optional)
    localizable = true, // (optional)
    position = 1, // (optional)
    scopable = true, // (optional)
    type = "select", // (optional)
    usable_in_grid = true, // (optional)
    validation = mapOf(
        "max_length" to 64,
        "min_length" to 3
    ), // (optional)
)
```
