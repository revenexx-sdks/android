```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.PaymentsLedger
import com.revenexx.enums.PaymentStatus
import com.revenexx.enums.PaymentMethodKind
import com.revenexx.enums.PaymentDunningStage

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val paymentsLedger = PaymentsLedger(client)

val result = paymentsLedger.paymentsList(
    limit = 1, // (optional)
    offset = 1, // (optional)
    order = "created_at.desc", // (optional)
    cart_id = "", // (optional)
    contact_id = "", // (optional)
    status = PaymentStatus.CREATED, // (optional)
    order_ref = "ORD-10042", // (optional)
    method_code = "invoice", // (optional)
    kind = PaymentMethodKind.SELF_MANAGED, // (optional)
    provider = "stripe", // (optional)
    dunning_stage = PaymentDunningStage.NONE, // (optional)
    idempotency_key = "checkout-2f9c41", // (optional)
)
```
