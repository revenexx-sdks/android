```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.Orders;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

Orders orders = new Orders(client);

orders.ordersPlace(
    List.of(), // items 
    Map.of("a", "b"), // billing_address (optional)
    Map.of("a", "b"), // buyer (optional)
    "", // cart_id (optional)
    "", // channel_id (optional)
    "", // contact_id (optional)
    "", // currency (optional)
    "", // customer_order_number (optional)
    0, // grand_total (optional)
    "", // market_id (optional)
    Map.of("a", "b"), // metadata (optional)
    "", // organization_id (optional)
    Map.of("a", "b"), // payment (optional)
    Map.of("a", "b"), // shipping (optional)
    Map.of("a", "b"), // shipping_address (optional)
    0, // shipping_total (optional)
    Map.of("a", "b"), // user_data (optional)
    new CoroutineCallback<>((result, error) -> {
        if (error != null) {
            error.printStackTrace();
            return;
        }

        Log.d("RevenexxAPIRevenexx", result.toString());
    })
);

```
