```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.ProductsDataModel;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

ProductsDataModel productsDataModel = new ProductsDataModel(client);

productsDataModel.productsFamilyVariantsUpdate(
    "", // id 
    Map.of(
        "0", "colour",
        "1", "size"
    ), // axes (optional)
    "clothing_by_colour_size", // code (optional)
    "", // family_id (optional)
    Map.of(
        "de", "Nach Farbe und Größe",
        "en", "By colour and size"
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
