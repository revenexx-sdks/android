```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.InventoriesReservations;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

InventoriesReservations inventoriesReservations = new InventoriesReservations(client);

inventoriesReservations.inventoriesReserve(
    "SO-2026-000123", // order_ref 
    "2026-01-01T12:00:00Z", // expires_at (optional)
    List.of(), // items (optional)
    "main", // location_code (optional)
    "", // product_id (optional)
    2, // quantity (optional)
    Map.of("a", "b"), // ship_to (optional)
    "ACME-4711-BLK", // sku (optional)
    new CoroutineCallback<>((result, error) -> {
        if (error != null) {
            error.printStackTrace();
            return;
        }

        Log.d("Revenexx", result.toString());
    })
);

```
