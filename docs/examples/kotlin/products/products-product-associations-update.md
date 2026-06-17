```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.Products

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val products = Products(client)

val result = products.productsProductAssociationsUpdate(
    id = "", 
    association_type_id = "", // (optional)
    position = 0, // (optional)
    product_id = "", // (optional)
    quantity = 0, // (optional)
    target_product_id = "", // (optional)
)
```
