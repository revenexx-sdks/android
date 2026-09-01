```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.Products

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val products = Products(client)

val result = products.productsProductAssociationsCreate(
    association_type_id = "", 
    product_id = "", 
    target_product_id = "", 
    position = 1, // (optional)
    quantity = 4, // (optional)
)
```
