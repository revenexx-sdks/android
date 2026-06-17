```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.Products;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

Products products = new Products(client);

products.productsAttributesUpdate(
    "", // id 
    "", // code (optional)
    Map.of("a", "b"), // config (optional)
    "", // entity_ref (optional)
    "", // entity_type (optional)
    "", // group_id (optional)
    false, // is_filterable (optional)
    false, // is_unique (optional)
    Map.of("a", "b"), // labels (optional)
    false, // localizable (optional)
    0, // position (optional)
    false, // scopable (optional)
    "", // type (optional)
    false, // usable_in_grid (optional)
    Map.of("a", "b"), // validation (optional)
    new CoroutineCallback<>((result, error) -> {
        if (error != null) {
            error.printStackTrace();
            return;
        }

        Log.d("RevenexxAPIRevenexx", result.toString());
    })
);

```
