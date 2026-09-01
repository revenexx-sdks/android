```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.PaymentsLedger;
import com.revenexx.enums.PaymentStatus;
import com.revenexx.enums.PaymentMethodKind;
import com.revenexx.enums.PaymentDunningStage;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

PaymentsLedger paymentsLedger = new PaymentsLedger(client);

paymentsLedger.paymentsList(
    1, // limit (optional)
    1, // offset (optional)
    "created_at.desc", // order (optional)
    "", // cart_id (optional)
    "", // contact_id (optional)
    PaymentStatus.CREATED, // status (optional)
    "ORD-10042", // order_ref (optional)
    "invoice", // method_code (optional)
    PaymentMethodKind.SELF_MANAGED, // kind (optional)
    "stripe", // provider (optional)
    PaymentDunningStage.NONE, // dunning_stage (optional)
    "checkout-2f9c41", // idempotency_key (optional)
    new CoroutineCallback<>((result, error) -> {
        if (error != null) {
            error.printStackTrace();
            return;
        }

        Log.d("Revenexx", result.toString());
    })
);

```
