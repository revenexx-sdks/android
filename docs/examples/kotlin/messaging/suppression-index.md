```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.Messaging
import com.revenexx.enums.Scope
import com.revenexx.enums.Reason

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val messaging = Messaging(client)

val result = messaging.suppressionIndex(
    channel = "", // (optional)
    scope = scope.ALL, // (optional)
    reason = reason.HARD_BOUNCE, // (optional)
    address = "", // (optional)
    limit = 1, // (optional)
)
```
