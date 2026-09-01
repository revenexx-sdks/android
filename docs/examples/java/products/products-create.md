```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.Products;
import com.revenexx.enums.ProductsKind;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

Products products = new Products(client);

products.productsCreate(
    "ACME-4711-BLK", // sku 
    Map.of(
        "channel_locale_specific", Map.of(
            "b2b", Map.of(
                "de_DE", Map.of(
                    "description", "Staffelpreise auf Anfrage."
                )
            )
        ),
        "channel_specific", Map.of(
            "b2b", Map.of(
                "minimum_order_quantity", 6
            )
        ),
        "common", Map.of(
            "colour", "black",
            "manufacturer_aid", "4711-BLK",
            "net_weight", 2.4
        ),
        "locale_specific", Map.of(
            "de_DE", Map.of(
                "description", "Bürstenloser Motor, 2 Akkus im Set.",
                "name", "Akku-Bohrschrauber 18V"
            ),
            "en_GB", Map.of(
                "name", "18V cordless drill"
            )
        )
    ), // attribute_values (optional)
    Map.of(
        "computed_at", "2026-01-01T12:00:00Z",
        "filled", 9,
        "missing", List.of("net_weight", "packaging_unit", "safety_datasheet"),
        "ratio", 0.75,
        "required", 12
    ), // completeness (optional)
    "2026-01-01T12:00:00Z", // deleted_at (optional)
    true, // enabled (optional)
    "", // family_id (optional)
    "", // family_variant_id (optional)
    ProductsKind.SIMPLE, // kind (optional)
    "", // parent_id (optional)
    Map.of(
        "PRODUCT_SET", Map.of(
            "product_models", List.of(),
            "products", List.of(Map.of(
        "identifier", "ACME-4711-CASTER",
        "quantity", 4
    ))
        )
    ), // quantified_associations (optional)
    "standard", // tax_class (optional)
    new CoroutineCallback<>((result, error) -> {
        if (error != null) {
            error.printStackTrace();
            return;
        }

        Log.d("Revenexx", result.toString());
    })
);

```
