```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.Orders
import com.revenexx.enums.OrderReturnSettlement

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val orders = Orders(client)

val result = orders.ordersReturnsComplete(
    id = "", 
    rid = "", 
    resolution = OrderReturnSettlement.REFUND, // (optional)
)
```
