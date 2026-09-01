```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.InventoriesStock;
import com.revenexx.enums.InventoriesVocabulariesGetName;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

InventoriesStock inventoriesStock = new InventoriesStock(client);

inventoriesStock.inventoriesVocabulariesGet(
    InventoriesVocabulariesGetName.LOCATION_TYPES, // name 
    new CoroutineCallback<>((result, error) -> {
        if (error != null) {
            error.printStackTrace();
            return;
        }

        Log.d("Revenexx", result.toString());
    })
);

```
