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
    "DHL", // carrier (optional)
    Map.of(
        "warehouse", "HAM-1"
    ), // metadata (optional)
    "DEL-000123", // number (optional)
    List.of(), // positions (optional)
    "2026-01-01T12:00:00Z", // shipped_at (optional)
    "00340434161234567890", // tracking_code (optional)
    "https://example.com/track/00340434161234567890", // tracking_url (optional)
    new CoroutineCallback<>((result, error) -> {
        if (error != null) {
            error.printStackTrace();
            return;
        }

        Log.d("Revenexx", result.toString());
    })
);

```
