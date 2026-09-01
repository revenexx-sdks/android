```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.ShippingMethods;
import com.revenexx.enums.PricingType;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

ShippingMethods shippingMethods = new ShippingMethods(client);

shippingMethods.shippingMethodsList(
    1, // limit (optional)
    1, // offset (optional)
    "position.asc", // order (optional)
    "express", // code (optional)
    true, // enabled (optional)
    PricingType.FIXED, // pricing_type (optional)
    "8a4d1c7e-2b93-4f61-b0d2-6c5a9e3f1a44", // carrier_id (optional)
    "acme-parcel", // carrier (optional)
    "reduced", // tax_class (optional)
    new CoroutineCallback<>((result, error) -> {
        if (error != null) {
            error.printStackTrace();
            return;
        }

        Log.d("Revenexx", result.toString());
    })
);

```
