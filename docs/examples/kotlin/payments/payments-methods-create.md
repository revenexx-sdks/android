```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.Payments
import com.revenexx.enums.PaymentFeeType
import com.revenexx.enums.PaymentMethodKind

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val payments = Payments(client)

val result = payments.paymentsMethodsCreate(
    code = "", 
    name = "", 
    countries = listOf(), // (optional)
    description = "", // (optional)
    enabled = false, // (optional)
    fee_amount = 0, // (optional)
    fee_currency = "", // (optional)
    fee_type = PaymentFeeType.NONE, // (optional)
    kind = PaymentMethodKind.SELF_MANAGED, // (optional)
    labels = mapOf( "a" to "b" ), // (optional)
    max_order_value = 0, // (optional)
    metadata = mapOf( "a" to "b" ), // (optional)
    min_order_value = 0, // (optional)
    position = 0, // (optional)
    provider = "", // (optional)
    provider_method = "", // (optional)
)
```
