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
    billing_address = mapOf(
        "city" to "Berlin",
        "company" to "Beispiel Industrietechnik GmbH",
        "country" to "DE",
        "name" to "Anna Berger",
        "street" to "Musterstraße 12",
        "zip" to "10115"
    ), // (optional)
    buyer = mapOf(
        "company" to "Beispiel Industrietechnik GmbH",
        "customer_number" to "K-10042",
        "email" to "anna.berger@example.com",
        "name" to "Anna Berger"
    ), // (optional)
    customer_order_number = "PO-2026-0042", // (optional)
    metadata = mapOf(
        "erp_batch" to "2026-W32"
    ), // (optional)
    shipping_address = mapOf(
        "city" to "Berlin",
        "company" to "Beispiel Industrietechnik GmbH",
        "country" to "DE",
        "name" to "Anna Berger",
        "street" to "Musterstraße 12",
        "zip" to "10115"
    ), // (optional)
    user_data = mapOf(
        "campaign" to "spring-catalogue",
        "source" to "webshop"
    ), // (optional)
)
```
