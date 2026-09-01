```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.Carts;
import com.revenexx.enums.CartMergeStrategy;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

Carts carts = new Carts(client);

carts.cartsClaim(
    "", // contact_id 
    "a1b2c3d4e5f6", // session_key 
    CartMergeStrategy.MERGE, // strategy (optional)
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
