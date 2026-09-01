```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.ProductsAssets;
import com.revenexx.enums.AssetsSource;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

ProductsAssets productsAssets = new ProductsAssets(client);

productsAssets.productsAssetsCreate(
    "", // asset_family_id 
    "acme-4711-blk_packshot_1", // code 
    Map.of(
        "common", Map.of(
            "copyright", "© Acme Tools",
            "expires_on", "2028-12-31"
        ),
        "locale_specific", Map.of(
            "de_DE", Map.of(
                "alt_text", "Akku-Bohrschrauber, freigestellt"
            )
        )
    ), // attribute_values (optional)
    "packshots/acme-4711-blk_1.jpg", // delivery_path (optional)
    "https://cdn.example.com/packshots/acme-4711-blk_1.jpg", // external_url (optional)
    AssetsSource.STORAGE, // source (optional)
    "ast_01J8ZQ0000000000000000", // storage_asset_id (optional)
    new CoroutineCallback<>((result, error) -> {
        if (error != null) {
            error.printStackTrace();
            return;
        }

        Log.d("Revenexx", result.toString());
    })
);

```
