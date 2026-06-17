```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.Payments;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

Payments payments = new Payments(client);

payments.paymentsProvidersUpdate(
    "", // id 
    Map.of("a", "b"), // credentials (optional)
    false, // enabled (optional)
    "", // name (optional)
    Map.of("a", "b"), // options (optional)
    "", // provider (optional)
    false, // test_mode (optional)
    "", // webhook_secret (optional)
    new CoroutineCallback<>((result, error) -> {
        if (error != null) {
            error.printStackTrace();
            return;
        }

        Log.d("RevenexxAPIRevenexx", result.toString());
    })
);

```
