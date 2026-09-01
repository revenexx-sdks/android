```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.ProductsDataModel;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

ProductsDataModel productsDataModel = new ProductsDataModel(client);

productsDataModel.productsAttributesList(
    1, // limit (optional)
    1, // offset (optional)
    "created_at.desc", // order (optional)
    "", // id (optional)
    "net_weight", // code (optional)
    "product", // entity_type (optional)
    "brand", // entity_ref (optional)
    "select", // type (optional)
    "", // group_id (optional)
    true, // localizable (optional)
    true, // scopable (optional)
    true, // is_unique (optional)
    true, // is_filterable (optional)
    true, // usable_in_grid (optional)
    "{}", // validation (optional)
    "{}", // config (optional)
    "{}", // labels (optional)
    1, // position (optional)
    "2026-01-01T12:00:00Z", // created_at (optional)
    "2026-01-01T12:00:00Z", // updated_at (optional)
    new CoroutineCallback<>((result, error) -> {
        if (error != null) {
            error.printStackTrace();
            return;
        }

        Log.d("Revenexx", result.toString());
    })
);

```
