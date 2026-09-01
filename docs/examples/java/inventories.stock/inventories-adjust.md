```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.InventoriesStock;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

InventoriesStock inventoriesStock = new InventoriesStock(client);

inventoriesStock.inventoriesAdjust(
    List.of(), // items (optional)
    "main", // location_code (optional)
    "", // product_id (optional)
    -3, // quantity (optional)
    "Stocktake 2026-03, two units damaged", // reason (optional)
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
