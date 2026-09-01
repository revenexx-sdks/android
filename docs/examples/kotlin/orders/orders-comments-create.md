```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.Orders
import com.revenexx.enums.OrderCommentVisibility

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val orders = Orders(client)

val result = orders.ordersCommentsCreate(
    id = "", 
    body = "Called the customer, delivery agreed for next week.", 
    author = "service-desk", // (optional)
    visibility = OrderCommentVisibility.INTERNAL, // (optional)
)
```
