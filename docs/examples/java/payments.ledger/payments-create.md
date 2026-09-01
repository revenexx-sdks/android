```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.PaymentsLedger;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

PaymentsLedger paymentsLedger = new PaymentsLedger(client);

paymentsLedger.paymentsCreate(
    49.9, // amount 
    "invoice", // method_code 
    "", // cart_id (optional)
    "", // contact_id (optional)
    "DE", // country (optional)
    "EUR", // currency (optional)
    "checkout-2f9c41", // idempotency_key (optional)
    Map.of(
        "order_source", "web"
    ), // metadata (optional)
    "ORD-10042", // order_ref (optional)
    "https://shop.example.com/checkout/return", // return_url (optional)
    new CoroutineCallback<>((result, error) -> {
        if (error != null) {
            error.printStackTrace();
            return;
        }

        Log.d("Revenexx", result.toString());
    })
);

```
