```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.PaymentsProviders;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

PaymentsProviders paymentsProviders = new PaymentsProviders(client);

paymentsProviders.paymentsProvidersUpdate(
    "", // id 
    Map.of("a", "b"), // credentials (optional)
    true, // enabled (optional)
    "Stripe", // name (optional)
    Map.of(
        "capture_method", "automatic",
        "logo_url", "https://apps.example.com/payments/logos/stripe",
        "three_ds", false
    ), // options (optional)
    "stripe", // provider (optional)
    true, // test_mode (optional)
    "", // webhook_secret (optional)
    new CoroutineCallback<>((result, error) -> {
        if (error != null) {
            error.printStackTrace();
            return;
        }

        Log.d("Revenexx", result.toString());
    })
);

```
