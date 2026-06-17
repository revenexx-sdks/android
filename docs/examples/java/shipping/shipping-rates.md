```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.Shipping;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

Shipping shipping = new Shipping(client);

shipping.shippingRates(
    Map.of("a", "b"), // attributes (optional)
    "", // country (optional)
    "", // currency (optional)
    "", // market_id (optional)
    0, // order_value (optional)
    0, // quantity (optional)
    0, // weight (optional)
    new CoroutineCallback<>((result, error) -> {
        if (error != null) {
            error.printStackTrace();
            return;
        }

        Log.d("RevenexxAPIRevenexx", result.toString());
    })
);

```
