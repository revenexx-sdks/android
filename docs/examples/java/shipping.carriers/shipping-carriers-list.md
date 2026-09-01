```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.ShippingCarriers;
import com.revenexx.enums.ShippingCarriersListStatus;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

ShippingCarriers shippingCarriers = new ShippingCarriers(client);

shippingCarriers.shippingCarriersList(
    1, // limit (optional)
    1, // offset (optional)
    "position.asc", // order (optional)
    "acme-parcel", // code (optional)
    ShippingCarriersListStatus.ACTIVE, // status (optional)
    "express", // service_level (optional)
    new CoroutineCallback<>((result, error) -> {
        if (error != null) {
            error.printStackTrace();
            return;
        }

        Log.d("Revenexx", result.toString());
    })
);

```
