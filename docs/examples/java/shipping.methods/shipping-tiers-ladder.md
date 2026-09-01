```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.ShippingMethods;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

ShippingMethods shippingMethods = new ShippingMethods(client);

shippingMethods.shippingTiersLadder(
    "", // method_id 
    4.9, // base_price 
    5, // step 
    30, // to_value 
    0, // from_value (optional)
    true, // replace (optional)
    2, // step_price (optional)
    new CoroutineCallback<>((result, error) -> {
        if (error != null) {
            error.printStackTrace();
            return;
        }

        Log.d("Revenexx", result.toString());
    })
);

```
