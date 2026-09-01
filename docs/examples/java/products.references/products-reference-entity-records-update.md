```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.ProductsReferences;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

ProductsReferences productsReferences = new ProductsReferences(client);

productsReferences.productsReferenceEntityRecordsUpdate(
    "", // id 
    Map.of(
        "common", Map.of(
            "country", "DE",
            "founded", 1946
        ),
        "locale_specific", Map.of(
            "de_DE", Map.of(
                "description", "Werkzeughersteller aus Süddeutschland."
            )
        )
    ), // attribute_values (optional)
    "acme_tools", // code (optional)
    Map.of(
        "de", "Acme Tools",
        "en", "Acme Tools"
    ), // labels (optional)
    "", // reference_entity_id (optional)
    new CoroutineCallback<>((result, error) -> {
        if (error != null) {
            error.printStackTrace();
            return;
        }

        Log.d("Revenexx", result.toString());
    })
);

```
