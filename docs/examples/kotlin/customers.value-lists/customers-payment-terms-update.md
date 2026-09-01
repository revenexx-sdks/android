```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.CustomersValueLists
import com.revenexx.enums.Tone

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val customersValueLists = CustomersValueLists(client)

val result = customersValueLists.customersPaymentTermsUpdate(
    id = "", 
    description = "Invoice due 30 days after the delivery note.", // (optional)
    descriptions = mapOf(
        "de" to "Rechnung 30 Tage nach Lieferschein fällig.",
        "en" to "Invoice due 30 days after the delivery note."
    ), // (optional)
    is_default = true, // (optional)
    labels = mapOf(
        "de" to "Zahlbar in 30 Tagen",
        "en" to "Net 30 days"
    ), // (optional)
    position = 1, // (optional)
    title = "Net 30 days", // (optional)
    tone = tone.NEUTRAL, // (optional)
)
```
