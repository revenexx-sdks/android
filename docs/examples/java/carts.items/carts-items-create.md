```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.CartsItems;
import com.revenexx.enums.CartItemType;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

CartsItems cartsItems = new CartsItems(client);

cartsItems.cartsItemsCreate(
    "", // cart_id 
    Map.of(
        "colour", "RAL 7016",
        "finish", "brushed",
        "length_mm", 2400,
        "mounting", "wall"
    ), // configuration (optional)
    "EUR", // currency (optional)
    Map.of(
        "campaign", "spring-catalogue",
        "locale", "de-DE",
        "source", "storefront"
    ), // metadata (optional)
    "Hex bolt M8", // name (optional)
    1, // position (optional)
    "", // product_id (optional)
    9.99, // quantity (optional)
    "BOLT-M8-30", // sku (optional)
    Map.of("a", "b"), // snapshot (optional)
    19, // tax_rate (optional)
    CartItemType.PRODUCT, // type (optional)
    "pcs", // unit (optional)
    9.99, // unit_price (optional)
    new CoroutineCallback<>((result, error) -> {
        if (error != null) {
            error.printStackTrace();
            return;
        }

        Log.d("Revenexx", result.toString());
    })
);

```
