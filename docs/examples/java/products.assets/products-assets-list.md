```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.ProductsAssets;
import com.revenexx.enums.ProductsAssetsListSource;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

ProductsAssets productsAssets = new ProductsAssets(client);

productsAssets.productsAssetsList(
    1, // limit (optional)
    1, // offset (optional)
    "created_at.desc", // order (optional)
    "", // id (optional)
    "", // asset_family_id (optional)
    "acme-4711-blk_packshot_1", // code (optional)
    ProductsAssetsListSource.STORAGE, // source (optional)
    "ast_01J8ZQ0000000000000000", // storage_asset_id (optional)
    "packshots/acme-4711-blk_1.jpg", // delivery_path (optional)
    "https://cdn.example.com/packshots/acme-4711-blk_1.jpg", // external_url (optional)
    "{}", // attribute_values (optional)
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
