```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.ProductsDataModel;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

ProductsDataModel productsDataModel = new ProductsDataModel(client);

productsDataModel.productsFamilyAttributesUpdate(
    "", // id 
    "", // attribute_id (optional)
    "", // family_id (optional)
    true, // is_required (optional)
    1, // position (optional)
    Map.of(
        "0", "shop",
        "1", "b2b"
    ), // required_channels (optional)
    new CoroutineCallback<>((result, error) -> {
        if (error != null) {
            error.printStackTrace();
            return;
        }

        Log.d("Revenexx", result.toString());
    })
);

```
