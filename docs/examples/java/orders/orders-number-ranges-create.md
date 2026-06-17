```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.Orders;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

Orders orders = new Orders(client);

orders.ordersNumberRangesCreate(
    "", // code 
    "", // channel_id (optional)
    0, // counter (optional)
    Map.of("a", "b"), // metadata (optional)
    0, // padding (optional)
    0, // position_step (optional)
    "", // prefix (optional)
    0, // step (optional)
    "", // suffix (optional)
    new CoroutineCallback<>((result, error) -> {
        if (error != null) {
            error.printStackTrace();
            return;
        }

        Log.d("RevenexxAPIRevenexx", result.toString());
    })
);

```
