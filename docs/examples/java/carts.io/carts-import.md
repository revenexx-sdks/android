```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.CartsIo;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

CartsIo cartsIo = new CartsIo(client);

cartsIo.cartsImport(
    "", // contact_id (optional)
    "sku,name,quantity,unit_price
BOLT-M8-30,Hex bolt M8,100,0.12
NUT-M8,Hex nut M8,100,0.04
", // csv (optional)
    "Weekly order", // name (optional)
    Map.of(
        "cart", Map.of(
            "currency", "EUR",
            "name", "Weekly order"
        ),
        "items", List.of(Map.of(
        "name", "Hex bolt M8",
        "quantity", 100,
        "sku", "BOLT-M8-30",
        "unit_price", 0.12
    ))
    ), // payload (optional)
    "", // profile_id (optional)
    "a1b2c3d4e5f6", // session_key (optional)
    "", // target_cart_id (optional)
    new CoroutineCallback<>((result, error) -> {
        if (error != null) {
            error.printStackTrace();
            return;
        }

        Log.d("Revenexx", result.toString());
    })
);

```
