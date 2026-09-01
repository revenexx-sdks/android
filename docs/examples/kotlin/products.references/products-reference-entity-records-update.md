```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.ProductsReferences

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val productsReferences = ProductsReferences(client)

val result = productsReferences.productsReferenceEntityRecordsUpdate(
    id = "", 
    attribute_values = mapOf(
        "common" to mapOf(
            "country" to "DE",
            "founded" to 1946
        ),
        "locale_specific" to mapOf(
            "de_DE" to mapOf(
                "description" to "Werkzeughersteller aus Süddeutschland."
            )
        )
    ), // (optional)
    code = "acme_tools", // (optional)
    labels = mapOf(
        "de" to "Acme Tools",
        "en" to "Acme Tools"
    ), // (optional)
    reference_entity_id = "", // (optional)
)
```
