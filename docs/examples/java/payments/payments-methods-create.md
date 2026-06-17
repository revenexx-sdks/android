```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.Payments;
import com.revenexx.enums.PaymentFeeType;
import com.revenexx.enums.PaymentMethodKind;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

Payments payments = new Payments(client);

payments.paymentsMethodsCreate(
    "", // code 
    "", // name 
    List.of(), // countries (optional)
    "", // description (optional)
    false, // enabled (optional)
    0, // fee_amount (optional)
    "", // fee_currency (optional)
    PaymentFeeType.NONE, // fee_type (optional)
    PaymentMethodKind.SELF_MANAGED, // kind (optional)
    Map.of("a", "b"), // labels (optional)
    0, // max_order_value (optional)
    Map.of("a", "b"), // metadata (optional)
    0, // min_order_value (optional)
    0, // position (optional)
    "", // provider (optional)
    "", // provider_method (optional)
    new CoroutineCallback<>((result, error) -> {
        if (error != null) {
            error.printStackTrace();
            return;
        }

        Log.d("RevenexxAPIRevenexx", result.toString());
    })
);

```
