```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.ShippingCarriers;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

ShippingCarriers shippingCarriers = new ShippingCarriers(client);

shippingCarriers.shippingTracking(
    "acme-parcel", // carrier 
    "DE", // country (optional)
    "12345", // postal_code (optional)
    "ACME000000001DE", // tracking_code (optional)
    new CoroutineCallback<>((result, error) -> {
        if (error != null) {
            error.printStackTrace();
            return;
        }

        Log.d("Revenexx", result.toString());
    })
);

```
