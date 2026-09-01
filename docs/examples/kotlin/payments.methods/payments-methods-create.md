```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.PaymentsMethods
import com.revenexx.enums.PaymentFeeType
import com.revenexx.enums.PaymentMethodKind

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val paymentsMethods = PaymentsMethods(client)

val result = paymentsMethods.paymentsMethodsCreate(
    code = "invoice", 
    name = "Invoice", 
    countries = listOf("DE", "AT"), // (optional)
    description = "Pay within 14 days of the invoice date.", // (optional)
    enabled = true, // (optional)
    fee_amount = 2.5, // (optional)
    fee_currency = "EUR", // (optional)
    fee_type = PaymentFeeType.NONE, // (optional)
    kind = PaymentMethodKind.SELF_MANAGED, // (optional)
    labels = mapOf(
        "de" to "Rechnung",
        "en" to "Invoice"
    ), // (optional)
    max_order_value = 2500, // (optional)
    metadata = mapOf(
        "erp_payment_key" to "ZTRM01"
    ), // (optional)
    min_order_value = 10, // (optional)
    position = 0, // (optional)
    provider = "stripe", // (optional)
    provider_method = "card", // (optional)
)
```
