```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.ProductsDataModel;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

ProductsDataModel productsDataModel = new ProductsDataModel(client);

productsDataModel.productsMeasurementFamiliesCreate(
    "weight", // code 
    "kilogram", // standard_unit 
    Map.of(
        "de", "Gewicht",
        "en", "Weight"
    ), // labels (optional)
    Map.of(
        "0", Map.of(
            "code", "kilogram",
            "convert_factor", 1,
            "symbol", "kg"
        ),
        "1", Map.of(
            "code", "gram",
            "convert_factor", 0.001,
            "symbol", "g"
        )
    ), // units (optional)
    new CoroutineCallback<>((result, error) -> {
        if (error != null) {
            error.printStackTrace();
            return;
        }

        Log.d("Revenexx", result.toString());
    })
);

```
