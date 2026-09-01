```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.ProductsCategories;
import com.revenexx.enums.CategoriesRuleMatch;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

ProductsCategories productsCategories = new ProductsCategories(client);

productsCategories.productsCategoriesCreate(
    "cordless_drills", // code 
    Map.of(
        "de", "Akku-Bohrschrauber",
        "en", "Cordless drills"
    ), // labels (optional)
    "", // parent_id (optional)
    "tools/power_tools/cordless_drills", // path (optional)
    1, // position (optional)
    CategoriesRuleMatch.ALL, // rule_match (optional)
    Map.of(
        "conditions", List.of(Map.of(
        "field", "attribute:brand",
        "operator", "in",
        "value", List.of("acme", "globex")
    ), Map.of(
        "field", "enabled",
        "operator", "eq",
        "value", true
    ))
    ), // rules (optional)
    "2026-01-01T12:00:00Z", // rules_computed_at (optional)
    Map.of(
        "hero_asset", "packshots/cordless_drills_hero",
        "seo_title", "Cordless drills"
    ), // values (optional)
    new CoroutineCallback<>((result, error) -> {
        if (error != null) {
            error.printStackTrace();
            return;
        }

        Log.d("Revenexx", result.toString());
    })
);

```
