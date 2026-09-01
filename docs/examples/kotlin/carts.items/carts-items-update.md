```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.CartsItems
import com.revenexx.enums.CartItemType

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val cartsItems = CartsItems(client)

val result = cartsItems.cartsItemsUpdate(
    cart_id = "", 
    id = "", 
    configuration = mapOf(
        "colour" to "RAL 7016",
        "finish" to "brushed",
        "length_mm" to 2400,
        "mounting" to "wall"
    ), // (optional)
    currency = "EUR", // (optional)
    metadata = mapOf(
        "campaign" to "spring-catalogue",
        "locale" to "de-DE",
        "source" to "storefront"
    ), // (optional)
    name = "Hex bolt M8", // (optional)
    position = 1, // (optional)
    product_id = "", // (optional)
    quantity = 9.99, // (optional)
    sku = "BOLT-M8-30", // (optional)
    snapshot = mapOf( "a" to "b" ), // (optional)
    tax_rate = 19, // (optional)
    type = CartItemType.PRODUCT, // (optional)
    unit = "pcs", // (optional)
    unit_price = 9.99, // (optional)
)
```
