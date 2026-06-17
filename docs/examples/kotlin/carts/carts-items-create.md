```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.Carts
import com.revenexx.enums.CartItemType

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val carts = Carts(client)

val result = carts.cartsItemsCreate(
    cart_id = "", 
    configuration = mapOf( "a" to "b" ), // (optional)
    currency = "", // (optional)
    metadata = mapOf( "a" to "b" ), // (optional)
    name = "", // (optional)
    position = 0, // (optional)
    product_id = "", // (optional)
    quantity = 0, // (optional)
    sku = "", // (optional)
    snapshot = mapOf( "a" to "b" ), // (optional)
    tax_rate = 0, // (optional)
    type = CartItemType.PRODUCT, // (optional)
    unit = "", // (optional)
    unit_price = 0, // (optional)
)
```
