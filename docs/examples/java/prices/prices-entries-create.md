```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.Prices;
import com.revenexx.enums.PriceEntryType;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

Prices prices = new Prices(client);

prices.pricesEntriesCreate(
    "", // list_id 
    Map.of(
        "imported_batch", "2026-02-14",
        "source_system", "erp"
    ), // metadata (optional)
    PriceEntryType.STANDARD, // price_type (optional)
    "", // product_id (optional)
    9.99, // quantity_min (optional)
    "BOLT-M8-30", // sku (optional)
    "pcs", // unit (optional)
    9.99, // unit_price (optional)
    "2026-03-01T00:00:00Z", // valid_from (optional)
    "2026-03-31T23:59:59Z", // valid_until (optional)
    new CoroutineCallback<>((result, error) -> {
        if (error != null) {
            error.printStackTrace();
            return;
        }

        Log.d("Revenexx", result.toString());
    })
);

```
