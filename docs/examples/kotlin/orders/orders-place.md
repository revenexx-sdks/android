```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.Orders

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val orders = Orders(client)

val result = orders.ordersPlace(
    items = listOf(), 
    billing_address = mapOf( "a" to "b" ), // (optional)
    buyer = mapOf( "a" to "b" ), // (optional)
    cart_id = "", // (optional)
    channel_id = "", // (optional)
    contact_id = "", // (optional)
    currency = "", // (optional)
    customer_order_number = "", // (optional)
    grand_total = 0, // (optional)
    market_id = "", // (optional)
    metadata = mapOf( "a" to "b" ), // (optional)
    organization_id = "", // (optional)
    payment = mapOf( "a" to "b" ), // (optional)
    shipping = mapOf( "a" to "b" ), // (optional)
    shipping_address = mapOf( "a" to "b" ), // (optional)
    shipping_total = 0, // (optional)
    user_data = mapOf( "a" to "b" ), // (optional)
)
```
