```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.Orders;
import com.revenexx.enums.OrderReturnRefusal;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

Orders orders = new Orders(client);

orders.ordersReturnsReject(
    "", // id 
    "", // rid 
    "Returned outside the agreed window", // reason (optional)
    OrderReturnRefusal.WEAR_AND_TEAR, // resolution (optional)
    new CoroutineCallback<>((result, error) -> {
        if (error != null) {
            error.printStackTrace();
            return;
        }

        Log.d("Revenexx", result.toString());
    })
);

```
