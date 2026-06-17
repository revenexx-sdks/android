```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.Carts;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

Carts carts = new Carts(client);

carts.cartsUpdate(
    "", // id 
    "", // channel_id (optional)
    "", // currency (optional)
    "", // market_id (optional)
    Map.of("a", "b"), // metadata (optional)
    "", // name (optional)
    new CoroutineCallback<>((result, error) -> {
        if (error != null) {
            error.printStackTrace();
            return;
        }

        Log.d("RevenexxAPIRevenexx", result.toString());
    })
);

```
