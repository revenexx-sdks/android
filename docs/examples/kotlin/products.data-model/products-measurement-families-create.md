```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.ProductsDataModel

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val productsDataModel = ProductsDataModel(client)

val result = productsDataModel.productsMeasurementFamiliesCreate(
    code = "weight", 
    standard_unit = "kilogram", 
    labels = mapOf(
        "de" to "Gewicht",
        "en" to "Weight"
    ), // (optional)
    units = mapOf(
        "0" to mapOf(
            "code" to "kilogram",
            "convert_factor" to 1,
            "symbol" to "kg"
        ),
        "1" to mapOf(
            "code" to "gram",
            "convert_factor" to 0.001,
            "symbol" to "g"
        )
    ), // (optional)
)
```
