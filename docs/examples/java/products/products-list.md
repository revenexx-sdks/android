```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.Products;
import com.revenexx.enums.Kind;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

Products products = new Products(client);

products.productsList(
    1, // limit (optional)
    1, // offset (optional)
    "created_at.desc", // order (optional)
    "", // id (optional)
    "ACME-4711-BLK", // sku (optional)
    Kind.SIMPLE, // kind (optional)
    "", // parent_id (optional)
    "", // family_id (optional)
    "", // family_variant_id (optional)
    true, // enabled (optional)
    "standard", // tax_class (optional)
    "{}", // attribute_values (optional)
    "Akku-Bohrschrauber 18V", // label (optional)
    "{}", // quantified_associations (optional)
    "{}", // completeness (optional)
    "2026-01-01T12:00:00Z", // created_at (optional)
    "2026-01-01T12:00:00Z", // updated_at (optional)
    "2026-01-01T12:00:00Z", // deleted_at (optional)
    new CoroutineCallback<>((result, error) -> {
        if (error != null) {
            error.printStackTrace();
            return;
        }

        Log.d("Revenexx", result.toString());
    })
);

```
