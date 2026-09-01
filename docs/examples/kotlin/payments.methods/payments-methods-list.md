```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.PaymentsMethods
import com.revenexx.enums.PaymentMethodKind

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val paymentsMethods = PaymentsMethods(client)

val result = paymentsMethods.paymentsMethodsList(
    limit = 1, // (optional)
    offset = 1, // (optional)
    order = "created_at.desc", // (optional)
    code = "invoice", // (optional)
    kind = PaymentMethodKind.SELF_MANAGED, // (optional)
    enabled = true, // (optional)
    provider = "stripe", // (optional)
)
```
