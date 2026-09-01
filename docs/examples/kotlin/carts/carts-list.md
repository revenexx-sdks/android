```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.Carts
import com.revenexx.enums.CartStatus

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val carts = Carts(client)

val result = carts.cartsList(
    id = "", // (optional)
    name = "Weekly order", // (optional)
    status = CartStatus.ACTIVE, // (optional)
    contact_id = "", // (optional)
    session_key = "a1b2c3d4e5f6", // (optional)
    channel_id = "", // (optional)
    currency = "EUR", // (optional)
    is_current = true, // (optional)
    item_count = 100, // (optional)
    subtotal = 12, // (optional)
    abandoned_at = "2026-01-01T12:00:00Z", // (optional)
    ordered_at = "2026-01-01T12:00:00Z", // (optional)
    order_ref = "SO-10042", // (optional)
    merged_into_cart_id = "", // (optional)
    created_at = "2026-01-01T12:00:00Z", // (optional)
    updated_at = "2026-01-01T12:00:00Z", // (optional)
    limit = 1, // (optional)
    offset = 1, // (optional)
    order = "created_at.desc", // (optional)
)
```
