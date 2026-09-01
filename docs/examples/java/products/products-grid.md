```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.Products;
import com.revenexx.enums.Kind;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

Products products = new Products(client);

products.productsGrid(
    1, // limit (optional)
    1, // offset (optional)
    "created_at.desc", // order (optional)
    "cordless drill", // q (optional)
    Kind.SIMPLE, // kind (optional)
    true, // enabled (optional)
    "", // family_id (optional)
    new CoroutineCallback<>((result, error) -> {
        if (error != null) {
            error.printStackTrace();
            return;
        }

        Log.d("Revenexx", result.toString());
    })
);

```
