```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.InventoriesLocations;
import com.revenexx.enums.InventoriesLocationsListType;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

InventoriesLocations inventoriesLocations = new InventoriesLocations(client);

inventoriesLocations.inventoriesLocationsList(
    50, // limit (optional)
    0, // offset (optional)
    "created_at.desc", // order (optional)
    "", // id (optional)
    "main", // code (optional)
    "Main warehouse", // name (optional)
    "{}", // labels (optional)
    InventoriesLocationsListType.WAREHOUSE, // type (optional)
    0, // priority (optional)
    true, // enabled (optional)
    "{}", // address (optional)
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
