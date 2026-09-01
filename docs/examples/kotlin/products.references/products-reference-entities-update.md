```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.ProductsReferences

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val productsReferences = ProductsReferences(client)

val result = productsReferences.productsReferenceEntitiesUpdate(
    id = "", 
    code = "brand", // (optional)
    image = "reference-entities/brand.svg", // (optional)
    labels = mapOf(
        "de" to "Marke",
        "en" to "Brand"
    ), // (optional)
)
```
