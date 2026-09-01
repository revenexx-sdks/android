```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.Orders;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

Orders orders = new Orders(client);

orders.ordersNumberRangesCreate(
    "order", // code 
    "", // channel_id (optional)
    123, // counter (optional)
    Map.of(
        "owner", "erp-sync"
    ), // metadata (optional)
    6, // padding (optional)
    10, // position_step (optional)
    "ORD-", // prefix (optional)
    1, // step (optional)
    "", // suffix (optional)
    new CoroutineCallback<>((result, error) -> {
        if (error != null) {
            error.printStackTrace();
            return;
        }

        Log.d("Revenexx", result.toString());
    })
);

```
