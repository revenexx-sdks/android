```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.InventoriesStock;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

InventoriesStock inventoriesStock = new InventoriesStock(client);

inventoriesStock.inventoriesStockList(
    50, // limit (optional)
    0, // offset (optional)
    "created_at.desc", // order (optional)
    "", // id (optional)
    "", // location_id (optional)
    "", // product_id (optional)
    "ACME-4711-BLK", // sku (optional)
    42, // on_hand (optional)
    5, // reserved (optional)
    10, // reorder_point (optional)
    "{}", // metadata (optional)
    "2026-01-01T12:00:00Z", // created_at (optional)
    "2026-01-01T12:00:00Z", // updated_at (optional)
    new CoroutineCallback<>((result, error) -> {
        if (error != null) {
            error.printStackTrace();
            return;
        }

        Log.d("Revenexx", result.toString());
    })
);

```
