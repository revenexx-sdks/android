```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.ProductsCategories
import com.revenexx.enums.CategoriesRuleMatch

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val productsCategories = ProductsCategories(client)

val result = productsCategories.productsCategoriesCreate(
    code = "cordless_drills", 
    labels = mapOf(
        "de" to "Akku-Bohrschrauber",
        "en" to "Cordless drills"
    ), // (optional)
    parent_id = "", // (optional)
    path = "tools/power_tools/cordless_drills", // (optional)
    position = 1, // (optional)
    rule_match = CategoriesRuleMatch.ALL, // (optional)
    rules = mapOf(
        "conditions" to listOf(mapOf(
        "field" to "attribute:brand",
        "operator" to "in",
        "value" to listOf("acme", "globex")
    ), mapOf(
        "field" to "enabled",
        "operator" to "eq",
        "value" to true
    ))
    ), // (optional)
    rules_computed_at = "2026-01-01T12:00:00Z", // (optional)
    values = mapOf(
        "hero_asset" to "packshots/cordless_drills_hero",
        "seo_title" to "Cordless drills"
    ), // (optional)
)
```
