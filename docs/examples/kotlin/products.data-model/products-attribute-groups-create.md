```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.ProductsDataModel

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val productsDataModel = ProductsDataModel(client)

val result = productsDataModel.productsAttributeGroupsCreate(
    code = "technical_attributes", 
    labels = mapOf(
        "de" to "Technische Attribute",
        "en" to "Technical attributes"
    ), // (optional)
    position = 1, // (optional)
)
```
