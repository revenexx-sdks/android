```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.Prices;
import com.revenexx.enums.PriceEndingRule;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

Prices prices = new Prices(client);

prices.pricesEntriesLadder(
    "", // list_id 
    9.99, // base_price 
    9.99, // discount_percent (optional)
    "", // product_id (optional)
    List.of(1, 10, 50), // quantities (optional)
    true, // replace (optional)
    PriceEndingRule.EXACT, // rounding (optional)
    "BOLT-M8-30", // sku (optional)
    "pcs", // unit (optional)
    new CoroutineCallback<>((result, error) -> {
        if (error != null) {
            error.printStackTrace();
            return;
        }

        Log.d("Revenexx", result.toString());
    })
);

```
