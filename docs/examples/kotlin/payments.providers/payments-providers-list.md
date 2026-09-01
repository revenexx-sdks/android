```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.PaymentsProviders

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val paymentsProviders = PaymentsProviders(client)

val result = paymentsProviders.paymentsProvidersList(
    limit = 1, // (optional)
    offset = 1, // (optional)
    order = "created_at.desc", // (optional)
    provider = "stripe", // (optional)
    enabled = true, // (optional)
    test_mode = true, // (optional)
)
```
