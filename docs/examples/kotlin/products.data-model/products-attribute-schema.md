```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.ProductsDataModel
import com.revenexx.enums.EntityType
import com.revenexx.enums.Kind

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val productsDataModel = ProductsDataModel(client)

val result = productsDataModel.productsAttributeSchema(
    family_id = "", // (optional)
    family_code = "", // (optional)
    entity_type = entity_type.PRODUCT, // (optional)
    entity_ref = "brand", // (optional)
    locale = "de_DE", // (optional)
    channel = "b2b", // (optional)
    kind = kind.SIMPLE, // (optional)
)
```
