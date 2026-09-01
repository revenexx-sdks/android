```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.ProductsCategories
import com.revenexx.enums.RuleMatch

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val productsCategories = ProductsCategories(client)

val result = productsCategories.productsCategoriesList(
    limit = 1, // (optional)
    offset = 1, // (optional)
    order = "created_at.desc", // (optional)
    id = "", // (optional)
    code = "cordless_drills", // (optional)
    parent_id = "", // (optional)
    path = "tools/power_tools/cordless_drills", // (optional)
    position = 1, // (optional)
    labels = "{}", // (optional)
    values = "{}", // (optional)
    rules = "{}", // (optional)
    rule_match = rule_match.ALL, // (optional)
    rules_computed_at = "2026-01-01T12:00:00Z", // (optional)
    created_at = "2026-01-01T12:00:00Z", // (optional)
    updated_at = "2026-01-01T12:00:00Z", // (optional)
)
```
