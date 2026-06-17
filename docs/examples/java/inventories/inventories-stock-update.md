```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.Inventories;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

Inventories inventories = new Inventories(client);

inventories.inventoriesStockUpdate(
    "", // id 
    "", // location_id (optional)
    Map.of("a", "b"), // metadata (optional)
    0, // on_hand (optional)
    "", // product_id (optional)
    0, // reorder_point (optional)
    0, // reserved (optional)
    "", // sku (optional)
    new CoroutineCallback<>((result, error) -> {
        if (error != null) {
            error.printStackTrace();
            return;
        }

        Log.d("RevenexxAPIRevenexx", result.toString());
    })
);

```
