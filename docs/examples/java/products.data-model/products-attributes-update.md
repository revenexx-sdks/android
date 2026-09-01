```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.ProductsDataModel;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

ProductsDataModel productsDataModel = new ProductsDataModel(client);

productsDataModel.productsAttributesUpdate(
    "", // id 
    "net_weight", // code (optional)
    Map.of(
        "reference_entity", "brand"
    ), // config (optional)
    "brand", // entity_ref (optional)
    "product", // entity_type (optional)
    "", // group_id (optional)
    true, // is_filterable (optional)
    true, // is_unique (optional)
    Map.of(
        "de", "Nettogewicht",
        "en", "Net weight"
    ), // labels (optional)
    true, // localizable (optional)
    1, // position (optional)
    true, // scopable (optional)
    "select", // type (optional)
    true, // usable_in_grid (optional)
    Map.of(
        "max_length", 64,
        "min_length", 3
    ), // validation (optional)
    new CoroutineCallback<>((result, error) -> {
        if (error != null) {
            error.printStackTrace();
            return;
        }

        Log.d("Revenexx", result.toString());
    })
);

```
