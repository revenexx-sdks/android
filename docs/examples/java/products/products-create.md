```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.Products;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

Products products = new Products(client);

products.productsCreate(
    "", // sku 
    Map.of("a", "b"), // attribute_values (optional)
    Map.of("a", "b"), // completeness (optional)
    "", // deleted_at (optional)
    false, // enabled (optional)
    "", // family_id (optional)
    "", // family_variant_id (optional)
    "", // kind (optional)
    "", // parent_id (optional)
    Map.of("a", "b"), // quantified_associations (optional)
    "", // tax_class (optional)
    new CoroutineCallback<>((result, error) -> {
        if (error != null) {
            error.printStackTrace();
            return;
        }

        Log.d("RevenexxAPIRevenexx", result.toString());
    })
);

```
