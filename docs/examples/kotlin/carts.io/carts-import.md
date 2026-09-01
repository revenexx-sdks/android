```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.CartsIo

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val cartsIo = CartsIo(client)

val result = cartsIo.cartsImport(
    contact_id = "", // (optional)
    csv = "sku,name,quantity,unit_price
BOLT-M8-30,Hex bolt M8,100,0.12
NUT-M8,Hex nut M8,100,0.04
", // (optional)
    name = "Weekly order", // (optional)
    payload = mapOf(
        "cart" to mapOf(
            "currency" to "EUR",
            "name" to "Weekly order"
        ),
        "items" to listOf(mapOf(
        "name" to "Hex bolt M8",
        "quantity" to 100,
        "sku" to "BOLT-M8-30",
        "unit_price" to 0.12
    ))
    ), // (optional)
    profile_id = "", // (optional)
    session_key = "a1b2c3d4e5f6", // (optional)
    target_cart_id = "", // (optional)
)
```
