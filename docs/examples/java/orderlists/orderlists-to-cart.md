```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.Orderlists;
import com.revenexx.enums.OrderListCartMode;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

Orderlists orderlists = new Orderlists(client);

orderlists.orderlistsToCart(
    "", // id 
    "", // cart_id (optional)
    "", // currency (optional)
    OrderListCartMode.APPEND, // mode (optional)
    new CoroutineCallback<>((result, error) -> {
        if (error != null) {
            error.printStackTrace();
            return;
        }

        Log.d("Revenexx", result.toString());
    })
);

```
