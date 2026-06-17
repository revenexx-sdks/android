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
    currency = "", // (optional)
    is_current = false, // (optional)
    market_id = "", // (optional)
    metadata = mapOf( "a" to "b" ), // (optional)
    name = "", // (optional)
    session_key = "", // (optional)
)
```
