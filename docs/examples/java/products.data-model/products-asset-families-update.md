```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.ProductsDataModel;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

ProductsDataModel productsDataModel = new ProductsDataModel(client);

productsDataModel.productsAssetFamiliesUpdate(
    "", // id 
    "packshots", // code (optional)
    Map.of(
        "de", "Packshots",
        "en", "Packshots"
    ), // labels (optional)
    Map.of(
        "allowed_extensions", List.of("jpg", "png"),
        "pattern", "{sku}_{index}",
        "source", "sku"
    ), // naming_convention (optional)
    new CoroutineCallback<>((result, error) -> {
        if (error != null) {
            error.printStackTrace();
            return;
        }

        Log.d("Revenexx", result.toString());
    })
);

```
