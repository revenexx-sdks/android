```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.Prices;
import com.revenexx.enums.PriceEntriesBulkMode;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

Prices prices = new Prices(client);

prices.pricesEntriesBulk(
    "", // list_id 
    List.of(), // entries 
    PriceEntriesBulkMode.UPSERT, // mode (optional)
    new CoroutineCallback<>((result, error) -> {
        if (error != null) {
            error.printStackTrace();
            return;
        }

        Log.d("Revenexx", result.toString());
    })
);

```
