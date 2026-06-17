```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.Prices;
import com.revenexx.enums.PriceEntryType;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

Prices prices = new Prices(client);

prices.pricesEntriesUpdate(
    "", // list_id 
    "", // id 
    Map.of("a", "b"), // metadata (optional)
    PriceEntryType.STANDARD, // price_type (optional)
    "", // product_id (optional)
    0, // quantity_min (optional)
    "", // sku (optional)
    "", // unit (optional)
    0, // unit_price (optional)
    "", // valid_from (optional)
    "", // valid_until (optional)
    new CoroutineCallback<>((result, error) -> {
        if (error != null) {
            error.printStackTrace();
            return;
        }

        Log.d("RevenexxAPIRevenexx", result.toString());
    })
);

```
