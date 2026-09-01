```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.ProductsDataModel;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

ProductsDataModel productsDataModel = new ProductsDataModel(client);

productsDataModel.productsAttributeOptionsUpdate(
    "", // id 
    "", // attribute_id (optional)
    "stainless_steel", // code (optional)
    Map.of(
        "de", "Edelstahl",
        "en", "Stainless steel"
    ), // labels (optional)
    1, // position (optional)
    Map.of(
        "hex", "#c0c0c0"
    ), // swatch (optional)
    new CoroutineCallback<>((result, error) -> {
        if (error != null) {
            error.printStackTrace();
            return;
        }

        Log.d("Revenexx", result.toString());
    })
);

```
