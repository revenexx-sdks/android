```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.InventoriesLocations;
import com.revenexx.enums.LocationType;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

InventoriesLocations inventoriesLocations = new InventoriesLocations(client);

inventoriesLocations.inventoriesLocationsCreate(
    "main", // code 
    "Main warehouse", // name 
    Map.of(
        "city", "Nuremberg",
        "country", "DE",
        "postal_code", "90402",
        "street", "Industriering 4"
    ), // address (optional)
    true, // enabled (optional)
    Map.of(
        "de", "Hauptlager",
        "en", "Main warehouse"
    ), // labels (optional)
    Map.of(
        "erp_site", "1000"
    ), // metadata (optional)
    0, // priority (optional)
    LocationType.WAREHOUSE, // type (optional)
    new CoroutineCallback<>((result, error) -> {
        if (error != null) {
            error.printStackTrace();
            return;
        }

        Log.d("Revenexx", result.toString());
    })
);

```
