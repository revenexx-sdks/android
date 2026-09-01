```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.ProductsAssets
import com.revenexx.enums.AssetsSource

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val productsAssets = ProductsAssets(client)

val result = productsAssets.productsAssetsCreate(
    asset_family_id = "", 
    code = "acme-4711-blk_packshot_1", 
    attribute_values = mapOf(
        "common" to mapOf(
            "copyright" to "© Acme Tools",
            "expires_on" to "2028-12-31"
        ),
        "locale_specific" to mapOf(
            "de_DE" to mapOf(
                "alt_text" to "Akku-Bohrschrauber, freigestellt"
            )
        )
    ), // (optional)
    delivery_path = "packshots/acme-4711-blk_1.jpg", // (optional)
    external_url = "https://cdn.example.com/packshots/acme-4711-blk_1.jpg", // (optional)
    source = AssetsSource.STORAGE, // (optional)
    storage_asset_id = "ast_01J8ZQ0000000000000000", // (optional)
)
```
