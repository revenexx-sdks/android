```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.Inventories;
import com.revenexx.enums.LocationType;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

Inventories inventories = new Inventories(client);

inventories.inventoriesLocationsUpdate(
    "", // id 
    Map.of("a", "b"), // address (optional)
    "", // code (optional)
    false, // enabled (optional)
    Map.of("a", "b"), // labels (optional)
    Map.of("a", "b"), // metadata (optional)
    "", // name (optional)
    0, // priority (optional)
    LocationType.WAREHOUSE, // type (optional)
    new CoroutineCallback<>((result, error) -> {
        if (error != null) {
            error.printStackTrace();
            return;
        }

        Log.d("RevenexxAPIRevenexx", result.toString());
    })
);

```
