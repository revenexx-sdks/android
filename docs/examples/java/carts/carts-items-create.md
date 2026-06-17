```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.Carts;
import com.revenexx.enums.CartItemType;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

Carts carts = new Carts(client);

carts.cartsItemsCreate(
    "", // cart_id 
    Map.of("a", "b"), // configuration (optional)
    "", // currency (optional)
    Map.of("a", "b"), // metadata (optional)
    "", // name (optional)
    0, // position (optional)
    "", // product_id (optional)
    0, // quantity (optional)
    "", // sku (optional)
    Map.of("a", "b"), // snapshot (optional)
    0, // tax_rate (optional)
    CartItemType.PRODUCT, // type (optional)
    "", // unit (optional)
    0, // unit_price (optional)
    new CoroutineCallback<>((result, error) -> {
        if (error != null) {
            error.printStackTrace();
            return;
        }

        Log.d("RevenexxAPIRevenexx", result.toString());
    })
);

```
