```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.PaymentsProviders

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val paymentsProviders = PaymentsProviders(client)

val result = paymentsProviders.paymentsProvidersUpdate(
    id = "", 
    credentials = mapOf( "a" to "b" ), // (optional)
    enabled = true, // (optional)
    name = "Stripe", // (optional)
    options = mapOf(
        "capture_method" to "automatic",
        "logo_url" to "https://apps.example.com/payments/logos/stripe",
        "three_ds" to false
    ), // (optional)
    provider = "stripe", // (optional)
    test_mode = true, // (optional)
    webhook_secret = "", // (optional)
)
```
