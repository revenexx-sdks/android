```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.ShippingMethods;
import com.revenexx.enums.ShippingMethodMatrixBasis;
import com.revenexx.enums.ShippingMethodPricingType;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

ShippingMethods shippingMethods = new ShippingMethods(client);

shippingMethods.shippingMethodsCreate(
    "express", // code 
    "Express delivery", // name 
    "acme-parcel", // carrier (optional)
    "8a4d1c7e-2b93-4f61-b0d2-6c5a9e3f1a44", // carrier_id (optional)
    List.of("DE", "AT", "CH"), // countries (optional)
    "EUR", // currency (optional)
    "Delivered by the next working day when ordered before the cut-off.", // description (optional)
    true, // enabled (optional)
    1, // eta_days_max (optional)
    1, // eta_days_min (optional)
    100, // free_above (optional)
    Map.of(
        "de", "Expressversand",
        "en", "Express delivery"
    ), // labels (optional)
    "volume_litres", // matrix_attribute (optional)
    ShippingMethodMatrixBasis.WEIGHT, // matrix_basis (optional)
    Map.of(
        "erp_key", "SHIP-EXPRESS",
        "printer", "label-2"
    ), // metadata (optional)
    1, // position (optional)
    9.9, // price (optional)
    ShippingMethodPricingType.FIXED, // pricing_type (optional)
    31.5, // quote_above (optional)
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
