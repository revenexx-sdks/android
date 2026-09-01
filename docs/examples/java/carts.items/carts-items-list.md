```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.CartsItems;
import com.revenexx.enums.CartItemType;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

CartsItems cartsItems = new CartsItems(client);

cartsItems.cartsItemsList(
    "", // cart_id 
    "", // id (optional)
    CartItemType.PRODUCT, // type (optional)
    "", // product_id (optional)
    "BOLT-M8-30", // sku (optional)
    "Hex bolt M8", // name (optional)
    100, // quantity (optional)
    "pcs", // unit (optional)
    0.12, // unit_price (optional)
    "EUR", // currency (optional)
    19, // tax_rate (optional)
    12, // line_total (optional)
    0, // position (optional)
    "2026-01-01T12:00:00Z", // created_at (optional)
    "2026-01-01T12:00:00Z", // updated_at (optional)
    1, // limit (optional)
    1, // offset (optional)
    "created_at.desc", // order (optional)
    new CoroutineCallback<>((result, error) -> {
        if (error != null) {
            error.printStackTrace();
            return;
        }

        Log.d("Revenexx", result.toString());
    })
);

```
