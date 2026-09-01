```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.Prices;
import com.revenexx.enums.PriceEntryType;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

Prices prices = new Prices(client);

prices.pricesEntriesList(
    "", // list_id 
    "", // id (optional)
    "", // product_id (optional)
    "BOLT-M8-30", // sku (optional)
    PriceEntryType.STANDARD, // price_type (optional)
    9.99, // quantity_min (optional)
    9.99, // unit_price (optional)
    "pcs", // unit (optional)
    "2026-01-01T12:00:00Z", // valid_from (optional)
    "2026-01-01T12:00:00Z", // valid_until (optional)
    "2026-01-01T12:00:00Z", // created_at (optional)
    "2026-01-01T12:00:00Z", // updated_at (optional)
    1, // limit (optional)
    1, // offset (optional)
    "created_at.desc", // order (optional)
    new CoroutineCallback<>((result, error) -> {
        if (error != null) {
            error.printStackTrace();
            return;
        }

        Log.d("Revenexx", result.toString());
    })
);

```
