```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.Carts

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val carts = Carts(client)

val result = carts.cartsUpdate(
    id = "", 
    channel_id = "", // (optional)
    currency = "", // (optional)
    market_id = "", // (optional)
    metadata = mapOf( "a" to "b" ), // (optional)
    name = "", // (optional)
)
```
