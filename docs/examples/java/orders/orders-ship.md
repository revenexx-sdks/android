```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.Orders;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

Orders orders = new Orders(client);

orders.ordersShip(
    "", // id 
    "", // carrier (optional)
    Map.of("a", "b"), // metadata (optional)
    "", // number (optional)
    List.of(), // positions (optional)
    "", // shipped_at (optional)
    "", // tracking_code (optional)
    "", // tracking_url (optional)
    new CoroutineCallback<>((result, error) -> {
        if (error != null) {
            error.printStackTrace();
            return;
        }

        Log.d("RevenexxAPIRevenexx", result.toString());
    })
);

```
