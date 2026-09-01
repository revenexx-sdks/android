```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.Orders;
import com.revenexx.enums.OrderStatus;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

Orders orders = new Orders(client);

orders.ordersReportsCustomerRollup(
    "2026-01-01T12:00:00Z", // as_of (optional)
    "", // cursor (optional)
    List.of(), // organization_ids (optional)
    OrderStatus.PENDING, // statuses (optional)
    new CoroutineCallback<>((result, error) -> {
        if (error != null) {
            error.printStackTrace();
            return;
        }

        Log.d("Revenexx", result.toString());
    })
);

```
