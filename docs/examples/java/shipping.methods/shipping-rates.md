```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.ShippingMethods;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

ShippingMethods shippingMethods = new ShippingMethods(client);

shippingMethods.shippingRates(
    "2026-01-01T12:00:00Z", // at (optional)
    Map.of(
        "volume_litres", 48
    ), // attributes (optional)
    "DE", // country (optional)
    "EUR", // currency (optional)
    "3f2b6d10-7c41-4c0a-9a35-2f5b8e0d9c11", // market_id (optional)
    129.9, // order_value (optional)
    129.9, // order_value_gross (optional)
    109.16, // order_value_net (optional)
    3, // quantity (optional)
    12.5, // weight (optional)
    "kg", // weight_unit (optional)
    new CoroutineCallback<>((result, error) -> {
        if (error != null) {
            error.printStackTrace();
            return;
        }

        Log.d("Revenexx", result.toString());
    })
);

```
