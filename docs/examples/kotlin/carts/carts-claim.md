```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.Carts
import com.revenexx.enums.CartMergeStrategy

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val carts = Carts(client)

val result = carts.cartsClaim(
    contact_id = "", 
    session_key = "a1b2c3d4e5f6", 
    strategy = CartMergeStrategy.MERGE, // (optional)
    target_cart_id = "", // (optional)
)
```
