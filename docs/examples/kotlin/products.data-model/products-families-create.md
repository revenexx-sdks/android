```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.ProductsDataModel

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val productsDataModel = ProductsDataModel(client)

val result = productsDataModel.productsFamiliesCreate(
    code = "power_tools", 
    image_attribute = "main_image", // (optional)
    label_attribute = "name", // (optional)
    labels = mapOf(
        "de" to "Elektrowerkzeuge",
        "en" to "Power tools"
    ), // (optional)
)
```
