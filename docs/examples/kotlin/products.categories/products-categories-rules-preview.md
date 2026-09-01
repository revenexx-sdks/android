```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.ProductsCategories
import com.revenexx.enums.CategoryRuleMatch

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val productsCategories = ProductsCategories(client)

val result = productsCategories.productsCategoriesRulesPreview(
    category_id = "", 
    conditions = listOf(), 
    rule_match = CategoryRuleMatch.ALL, // (optional)
)
```
