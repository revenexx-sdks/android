```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.ProductsAssets
import com.revenexx.enums.ProductsAssetsListSource

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val productsAssets = ProductsAssets(client)

val result = productsAssets.productsAssetsList(
    limit = 1, // (optional)
    offset = 1, // (optional)
    order = "created_at.desc", // (optional)
    id = "", // (optional)
    asset_family_id = "", // (optional)
    code = "acme-4711-blk_packshot_1", // (optional)
    source = Products.assets.listSource.STORAGE, // (optional)
    storage_asset_id = "ast_01J8ZQ0000000000000000", // (optional)
    delivery_path = "packshots/acme-4711-blk_1.jpg", // (optional)
    external_url = "https://cdn.example.com/packshots/acme-4711-blk_1.jpg", // (optional)
    attribute_values = "{}", // (optional)
    created_at = "2026-01-01T12:00:00Z", // (optional)
    updated_at = "2026-01-01T12:00:00Z", // (optional)
)
```
