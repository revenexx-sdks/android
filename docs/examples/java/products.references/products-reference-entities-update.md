```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.ProductsReferences;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

ProductsReferences productsReferences = new ProductsReferences(client);

productsReferences.productsReferenceEntitiesUpdate(
    "", // id 
    "brand", // code (optional)
    "reference-entities/brand.svg", // image (optional)
    Map.of(
        "de", "Marke",
        "en", "Brand"
    ), // labels (optional)
    new CoroutineCallback<>((result, error) -> {
        if (error != null) {
            error.printStackTrace();
            return;
        }

        Log.d("Revenexx", result.toString());
    })
);

```
