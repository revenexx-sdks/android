```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.ProductsCategories;
import com.revenexx.enums.Source;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

ProductsCategories productsCategories = new ProductsCategories(client);

productsCategories.productsProductCategoriesList(
    1, // limit (optional)
    1, // offset (optional)
    "created_at.desc", // order (optional)
    "", // id (optional)
    "", // product_id (optional)
    "", // category_id (optional)
    1, // position (optional)
    Source.MANUAL, // source (optional)
    "2026-01-01T12:00:00Z", // created_at (optional)
    new CoroutineCallback<>((result, error) -> {
        if (error != null) {
            error.printStackTrace();
            return;
        }

        Log.d("Revenexx", result.toString());
    })
);

```
