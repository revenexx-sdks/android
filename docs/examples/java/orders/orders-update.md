```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.Orders;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

Orders orders = new Orders(client);

orders.ordersUpdate(
    "", // id 
    Map.of("a", "b"), // billing_address (optional)
    Map.of("a", "b"), // buyer (optional)
    "", // customer_order_number (optional)
    Map.of("a", "b"), // metadata (optional)
    Map.of("a", "b"), // shipping_address (optional)
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
