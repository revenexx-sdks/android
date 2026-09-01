```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.ProductsDataModel

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val productsDataModel = ProductsDataModel(client)

val result = productsDataModel.productsAssociationTypesUpdate(
    id = "", 
    code = "cross_sell", // (optional)
    is_quantified = true, // (optional)
    is_two_way = true, // (optional)
    labels = mapOf(
        "de" to "Querverkauf",
        "en" to "Cross-sell"
    ), // (optional)
)
```
