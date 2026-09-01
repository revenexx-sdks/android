```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.ProductsCategories;
import com.revenexx.enums.ProductCategoriesSource;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

ProductsCategories productsCategories = new ProductsCategories(client);

productsCategories.productsProductCategoriesCreate(
    "", // category_id 
    "", // product_id 
    1, // position (optional)
    ProductCategoriesSource.MANUAL, // source (optional)
    new CoroutineCallback<>((result, error) -> {
        if (error != null) {
            error.printStackTrace();
            return;
        }

        Log.d("Revenexx", result.toString());
    })
);

```
