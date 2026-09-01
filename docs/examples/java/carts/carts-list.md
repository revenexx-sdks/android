```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.Carts;
import com.revenexx.enums.CartStatus;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

Carts carts = new Carts(client);

carts.cartsList(
    "", // id (optional)
    "Weekly order", // name (optional)
    CartStatus.ACTIVE, // status (optional)
    "", // contact_id (optional)
    "a1b2c3d4e5f6", // session_key (optional)
    "", // channel_id (optional)
    "EUR", // currency (optional)
    true, // is_current (optional)
    100, // item_count (optional)
    12, // subtotal (optional)
    "2026-01-01T12:00:00Z", // abandoned_at (optional)
    "2026-01-01T12:00:00Z", // ordered_at (optional)
    "SO-10042", // order_ref (optional)
    "", // merged_into_cart_id (optional)
    "2026-01-01T12:00:00Z", // created_at (optional)
    "2026-01-01T12:00:00Z", // updated_at (optional)
    1, // limit (optional)
    1, // offset (optional)
    "created_at.desc", // order (optional)
    new CoroutineCallback<>((result, error) -> {
        if (error != null) {
            error.printStackTrace();
            return;
        }

        Log.d("Revenexx", result.toString());
    })
);

```
