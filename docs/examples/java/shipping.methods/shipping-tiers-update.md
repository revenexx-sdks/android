```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.ShippingMethods;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

ShippingMethods shippingMethods = new ShippingMethods(client);

shippingMethods.shippingTiersUpdate(
    "", // method_id 
    "", // id 
    10, // from_value (optional)
    1, // position (optional)
    6.9, // price (optional)
    new CoroutineCallback<>((result, error) -> {
        if (error != null) {
            error.printStackTrace();
            return;
        }

        Log.d("Revenexx", result.toString());
    })
);

```
