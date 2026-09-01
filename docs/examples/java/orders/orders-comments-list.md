```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.Orders;
import com.revenexx.enums.OrderCommentVisibility;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

Orders orders = new Orders(client);

orders.ordersCommentsList(
    "", // id 
    "", // id_query (optional)
    "Called the customer, delivery agreed for next week.", // body (optional)
    OrderCommentVisibility.INTERNAL, // visibility (optional)
    "service-desk", // author (optional)
    "2026-01-01T12:00:00Z", // created_at (optional)
    50, // limit (optional)
    0, // offset (optional)
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
