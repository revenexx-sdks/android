```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.Shipping;
import com.revenexx.enums.ShippingMethodMatrixBasis;
import com.revenexx.enums.ShippingMethodPricingType;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

Shipping shipping = new Shipping(client);

shipping.shippingMethodsUpdate(
    "", // id 
    "", // carrier (optional)
    "", // code (optional)
    List.of(), // countries (optional)
    "", // currency (optional)
    "", // description (optional)
    false, // enabled (optional)
    0, // eta_days_max (optional)
    0, // eta_days_min (optional)
    0, // free_above (optional)
    Map.of("a", "b"), // labels (optional)
    "", // matrix_attribute (optional)
    ShippingMethodMatrixBasis.WEIGHT, // matrix_basis (optional)
    Map.of("a", "b"), // metadata (optional)
    "", // name (optional)
    0, // position (optional)
    0, // price (optional)
    ShippingMethodPricingType.FIXED, // pricing_type (optional)
    new CoroutineCallback<>((result, error) -> {
        if (error != null) {
            error.printStackTrace();
            return;
        }

        Log.d("RevenexxAPIRevenexx", result.toString());
    })
);

```
