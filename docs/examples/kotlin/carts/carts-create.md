```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.Carts

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val carts = Carts(client)

val result = carts.cartsCreate(
    channel_id = "", // (optional)
    contact_id = "", // (optional)
    currency = "EUR", // (optional)
    is_current = true, // (optional)
    metadata = mapOf(
        "campaign" to "spring-catalogue",
        "locale" to "de-DE",
        "source" to "storefront"
    ), // (optional)
    name = "Weekly order", // (optional)
    session_key = "a1b2c3d4e5f6", // (optional)
)
```
