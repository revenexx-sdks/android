```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.ProductsCategories;
import com.revenexx.enums.CategoryRuleMatch;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

ProductsCategories productsCategories = new ProductsCategories(client);

productsCategories.productsCategoriesRulesPreview(
    "", // category_id 
    List.of(), // conditions 
    CategoryRuleMatch.ALL, // rule_match (optional)
    new CoroutineCallback<>((result, error) -> {
        if (error != null) {
            error.printStackTrace();
            return;
        }

        Log.d("Revenexx", result.toString());
    })
);

```
