```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.Orders

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val orders = Orders(client)

val result = orders.ordersUpdate(
    id = "", 
    billing_address = mapOf( "a" to "b" ), // (optional)
    buyer = mapOf( "a" to "b" ), // (optional)
    customer_order_number = "", // (optional)
    metadata = mapOf( "a" to "b" ), // (optional)
    shipping_address = mapOf( "a" to "b" ), // (optional)
    user_data = mapOf( "a" to "b" ), // (optional)
)
```
