```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.Orders;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

Orders orders = new Orders(client);

orders.ordersUpdate(
    "", // id 
    Map.of(
        "city", "Berlin",
        "company", "Beispiel Industrietechnik GmbH",
        "country", "DE",
        "name", "Anna Berger",
        "street", "Musterstraße 12",
        "zip", "10115"
    ), // billing_address (optional)
    Map.of(
        "company", "Beispiel Industrietechnik GmbH",
        "customer_number", "K-10042",
        "email", "anna.berger@example.com",
        "name", "Anna Berger"
    ), // buyer (optional)
    "PO-2026-0042", // customer_order_number (optional)
    Map.of(
        "erp_batch", "2026-W32"
    ), // metadata (optional)
    Map.of(
        "city", "Berlin",
        "company", "Beispiel Industrietechnik GmbH",
        "country", "DE",
        "name", "Anna Berger",
        "street", "Musterstraße 12",
        "zip", "10115"
    ), // shipping_address (optional)
    Map.of(
        "campaign", "spring-catalogue",
        "source", "webshop"
    ), // user_data (optional)
    new CoroutineCallback<>((result, error) -> {
        if (error != null) {
            error.printStackTrace();
            return;
        }

        Log.d("Revenexx", result.toString());
    })
);

```
