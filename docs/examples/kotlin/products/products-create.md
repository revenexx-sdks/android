```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.Products
import com.revenexx.enums.ProductsKind

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val products = Products(client)

val result = products.productsCreate(
    sku = "ACME-4711-BLK", 
    attribute_values = mapOf(
        "channel_locale_specific" to mapOf(
            "b2b" to mapOf(
                "de_DE" to mapOf(
                    "description" to "Staffelpreise auf Anfrage."
                )
            )
        ),
        "channel_specific" to mapOf(
            "b2b" to mapOf(
                "minimum_order_quantity" to 6
            )
        ),
        "common" to mapOf(
            "colour" to "black",
            "manufacturer_aid" to "4711-BLK",
            "net_weight" to 2.4
        ),
        "locale_specific" to mapOf(
            "de_DE" to mapOf(
                "description" to "Bürstenloser Motor, 2 Akkus im Set.",
                "name" to "Akku-Bohrschrauber 18V"
            ),
            "en_GB" to mapOf(
                "name" to "18V cordless drill"
            )
        )
    ), // (optional)
    completeness = mapOf(
        "computed_at" to "2026-01-01T12:00:00Z",
        "filled" to 9,
        "missing" to listOf("net_weight", "packaging_unit", "safety_datasheet"),
        "ratio" to 0.75,
        "required" to 12
    ), // (optional)
    deleted_at = "2026-01-01T12:00:00Z", // (optional)
    enabled = true, // (optional)
    family_id = "", // (optional)
    family_variant_id = "", // (optional)
    kind = ProductsKind.SIMPLE, // (optional)
    parent_id = "", // (optional)
    quantified_associations = mapOf(
        "PRODUCT_SET" to mapOf(
            "product_models" to listOf(),
            "products" to listOf(mapOf(
        "identifier" to "ACME-4711-CASTER",
        "quantity" to 4
    ))
        )
    ), // (optional)
    tax_class = "standard", // (optional)
)
```
