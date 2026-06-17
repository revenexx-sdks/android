```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.Payments;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

Payments payments = new Payments(client);

payments.paymentsCreate(
    0, // amount 
    "", // method_code 
    "", // cart_id (optional)
    "", // contact_id (optional)
    "", // country (optional)
    "", // currency (optional)
    "", // idempotency_key (optional)
    Map.of("a", "b"), // metadata (optional)
    "", // order_ref (optional)
    "", // return_url (optional)
    new CoroutineCallback<>((result, error) -> {
        if (error != null) {
            error.printStackTrace();
            return;
        }

        Log.d("RevenexxAPIRevenexx", result.toString());
    })
);

```
