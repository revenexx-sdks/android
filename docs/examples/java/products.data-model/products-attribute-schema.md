```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.ProductsDataModel;
import com.revenexx.enums.EntityType;
import com.revenexx.enums.Kind;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

ProductsDataModel productsDataModel = new ProductsDataModel(client);

productsDataModel.productsAttributeSchema(
    "", // family_id (optional)
    "", // family_code (optional)
    EntityType.PRODUCT, // entity_type (optional)
    "brand", // entity_ref (optional)
    "de_DE", // locale (optional)
    "b2b", // channel (optional)
    Kind.SIMPLE, // kind (optional)
    new CoroutineCallback<>((result, error) -> {
        if (error != null) {
            error.printStackTrace();
            return;
        }

        Log.d("Revenexx", result.toString());
    })
);

```
