```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.ProductsCategories;
import com.revenexx.enums.RuleMatch;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

ProductsCategories productsCategories = new ProductsCategories(client);

productsCategories.productsCategoriesList(
    1, // limit (optional)
    1, // offset (optional)
    "created_at.desc", // order (optional)
    "", // id (optional)
    "cordless_drills", // code (optional)
    "", // parent_id (optional)
    "tools/power_tools/cordless_drills", // path (optional)
    1, // position (optional)
    "{}", // labels (optional)
    "{}", // values (optional)
    "{}", // rules (optional)
    RuleMatch.ALL, // rule_match (optional)
    "2026-01-01T12:00:00Z", // rules_computed_at (optional)
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
