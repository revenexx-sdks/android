```kotlin
import com.revenexx.Client
import com.revenexx.coroutines.CoroutineCallback
import com.revenexx.services.Messaging

val client = Client(context)
    .setEndpoint("https://api.revenexx.com") // Your API Endpoint
    .setApiKeyAuth("<API_KEY>") // A gateway-managed scoped API key (rvxk_…).

val messaging = Messaging(client)

val result = messaging.sendSend(
    channel = "", 
    template = "", 
    to = "", 
    attachments = listOf(), // (optional)
    data = mapOf( "a" to "b" ), // (optional)
    draft = true, // (optional)
    locale = "", // (optional)
    market = "", // (optional)
    send_at = "2026-01-01T12:00:00Z", // (optional)
)
```
