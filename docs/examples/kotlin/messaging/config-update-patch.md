```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.Messaging

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val messaging = Messaging(client)

val result = messaging.configUpdatePatch(
    default_locale = "", // (optional)
    defaults = listOf(), // (optional)
    product = "", // (optional)
    quiet_hours = listOf(), // (optional)
    support_email = "jane@example.com", // (optional)
)
```
