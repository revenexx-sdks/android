```java
import com.revenexx.Client;
import com.revenexx.coroutines.CoroutineCallback;
import com.revenexx.services.PaymentsMethods;
import com.revenexx.enums.PaymentFeeType;
import com.revenexx.enums.PaymentMethodKind;

Client client = new Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>"); // A gateway-managed scoped API key (rvxk_…).

PaymentsMethods paymentsMethods = new PaymentsMethods(client);

paymentsMethods.paymentsMethodsCreate(
    "invoice", // code 
    "Invoice", // name 
    List.of("DE", "AT"), // countries (optional)
    "Pay within 14 days of the invoice date.", // description (optional)
    true, // enabled (optional)
    2.5, // fee_amount (optional)
    "EUR", // fee_currency (optional)
    PaymentFeeType.NONE, // fee_type (optional)
    PaymentMethodKind.SELF_MANAGED, // kind (optional)
    Map.of(
        "de", "Rechnung",
        "en", "Invoice"
    ), // labels (optional)
    2500, // max_order_value (optional)
    Map.of(
        "erp_payment_key", "ZTRM01"
    ), // metadata (optional)
    10, // min_order_value (optional)
    0, // position (optional)
    "stripe", // provider (optional)
    "card", // provider_method (optional)
    new CoroutineCallback<>((result, error) -> {
        if (error != null) {
            error.printStackTrace();
            return;
        }

        Log.d("Revenexx", result.toString());
    })
);

```
