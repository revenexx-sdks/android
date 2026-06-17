```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.Carts

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val carts = Carts(client)

val result = carts.cartsImport(
    contact_id = "", // (optional)
    csv = "", // (optional)
    name = "", // (optional)
    payload = mapOf( "a" to "b" ), // (optional)
    profile_id = "", // (optional)
    session_key = "", // (optional)
    target_cart_id = "", // (optional)
)
```
