```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.Orderlists
import com.revenexx.enums.OrderListCartMode

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val orderlists = Orderlists(client)

val result = orderlists.orderlistsToCart(
    id = "", 
    cart_id = "", // (optional)
    currency = "", // (optional)
    mode = OrderListCartMode.APPEND, // (optional)
)
```
