```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.PaymentsMethods;
import com.revenexx.enums.PaymentMethodKind;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

PaymentsMethods paymentsMethods = new PaymentsMethods(client);

paymentsMethods.paymentsMethodsList(
    1, // limit (optional)
    1, // offset (optional)
    "created_at.desc", // order (optional)
    "invoice", // code (optional)
    PaymentMethodKind.SELF_MANAGED, // kind (optional)
    true, // enabled (optional)
    "stripe", // provider (optional)
    new CoroutineCallback<>((result, error) -> {
        if (error != null) {
            error.printStackTrace();
            return;
        }

        Log.d("Revenexx", result.toString());
    })
);

```
