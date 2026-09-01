```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.Messaging
import com.revenexx.enums.Reason
import com.revenexx.enums.Scope

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val messaging = Messaging(client)

val result = messaging.suppressionStore(
    address = "", 
    channel = "", 
    reason = reason.HARD_BOUNCE,
    expires_at = "2026-01-01T12:00:00Z", // (optional)
    note = "", // (optional)
    scope = scope.ALL, // (optional)
)
```
